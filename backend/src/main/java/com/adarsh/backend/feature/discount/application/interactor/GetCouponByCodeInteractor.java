package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.result.GetCouponByCodeResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.GetCouponByCodeUseCase;
import com.adarsh.backend.feature.discount.domain.exception.CouponNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCouponByCodeInteractor implements GetCouponByCodeUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetCouponByCodeInteractor.class);

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;

    @Override
    public GetCouponByCodeResult execute(String code) {
        String normalizedCode = code.toUpperCase().trim();
        logger.info(DiscountInteractorLogConstants.GET_COUPON_REQUEST, normalizedCode);

        Coupon coupon = couponQueryRepositoryPort.findByCode(normalizedCode)
                .orElseThrow(() -> new CouponNotFoundException(String.format(DiscountExceptionMessageConstants.COUPON_NOT_FOUND, normalizedCode)));

        return GetCouponByCodeResult.fromDomain(coupon);
    }
}
