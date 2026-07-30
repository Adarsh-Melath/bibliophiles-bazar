package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.command.UpdateCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateCouponResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.CouponCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.UpdateCouponUseCase;
import com.adarsh.backend.feature.discount.domain.exception.CouponNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCouponInteractor implements UpdateCouponUseCase {

    private static final Logger logger = LoggerFactory.getLogger(UpdateCouponInteractor.class);

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;
    private final CouponCommandRepositoryPort couponCommandRepositoryPort;

    @Override
    @Transactional
    public UpdateCouponResult execute(String code, UpdateCouponCommand command) {
        String normalizedCode = code.toUpperCase().trim();
        logger.info(DiscountInteractorLogConstants.UPDATE_COUPON_REQUEST, normalizedCode);

        Coupon existing = couponQueryRepositoryPort.findByCode(normalizedCode)
                .orElseThrow(() -> new CouponNotFoundException(String.format(DiscountExceptionMessageConstants.COUPON_NOT_FOUND, normalizedCode)));

        Coupon updated = new Coupon.Builder()
                .code(existing.getCode())
                .discountType(command.discountType() != null ? command.discountType() : existing.getDiscountType())
                .discountValue(command.discountValue() != null ? command.discountValue() : existing.getDiscountValue())
                .minOrderValue(command.minOrderValue() != null ? command.minOrderValue() : existing.getMinOrderValue())
                .maxDiscountAmount(command.maxDiscountAmount() != null ? command.maxDiscountAmount() : existing.getMaxDiscountAmount())
                .expiryDate(command.expiryDate() != null ? command.expiryDate() : existing.getExpiryDate())
                .isActive(command.isActive() != null ? command.isActive() : existing.isActive())
                .build();

        Coupon saved = couponCommandRepositoryPort.save(updated);
        logger.info(DiscountInteractorLogConstants.UPDATE_COUPON_SUCCESS, saved.getCode());

        return UpdateCouponResult.fromDomain(saved);
    }
}
