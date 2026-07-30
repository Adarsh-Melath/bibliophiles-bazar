package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.CouponCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.DeleteCouponUseCase;
import com.adarsh.backend.feature.discount.domain.exception.CouponNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCouponInteractor implements DeleteCouponUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DeleteCouponInteractor.class);

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;
    private final CouponCommandRepositoryPort couponCommandRepositoryPort;

    @Override
    @Transactional
    public void execute(String couponCode) {
        String normalizedCode = couponCode.toUpperCase().trim();
        logger.info(DiscountInteractorLogConstants.DELETE_COUPON_REQUEST, normalizedCode);

        if (!couponQueryRepositoryPort.existsByCode(normalizedCode)) {
            throw new CouponNotFoundException(String.format(DiscountExceptionMessageConstants.COUPON_NOT_FOUND, normalizedCode));
        }

        couponCommandRepositoryPort.deleteByCode(normalizedCode);
        logger.info(DiscountInteractorLogConstants.DELETE_COUPON_SUCCESS, normalizedCode);
    }
}
