package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorMessageConstants;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.ValidateCouponUseCase;
import com.adarsh.backend.feature.discount.domain.exception.CouponNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.InvalidCouponException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateCouponInteractor implements ValidateCouponUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCouponInteractor.class);

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;

    @Override
    public ApplyCouponResult execute(String couponCode, Double subtotal) {
        logger.info(DiscountInteractorLogConstants.VALIDATE_COUPON_REQUEST, couponCode, subtotal);

        if (subtotal == null || subtotal <= 0) {
            throw new InvalidCouponException(DiscountInteractorMessageConstants.INVALID_SUBTOTAL);
        }

        String normalizedCode = couponCode.toUpperCase().trim();
        Coupon coupon = couponQueryRepositoryPort.findByCode(normalizedCode)
                .orElseThrow(() -> new CouponNotFoundException(String.format(DiscountExceptionMessageConstants.COUPON_NOT_FOUND, normalizedCode)));

        if (!coupon.isApplicable(subtotal)) {
            throw new InvalidCouponException(DiscountInteractorMessageConstants.SUBTOTAL_REQUIREMENT_NOT_MET);
        }

        double discountAmount = coupon.calculateDiscount(subtotal);
        double finalTotal = Math.max(0.0, subtotal - discountAmount);

        return new ApplyCouponResult(normalizedCode, subtotal, discountAmount, finalTotal, DiscountInteractorMessageConstants.COUPON_VALID);
    }
}
