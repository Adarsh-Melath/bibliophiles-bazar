package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.command.CreateCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateCouponResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorMessageConstants;
import com.adarsh.backend.feature.discount.application.port.CouponCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.CreateCouponUseCase;
import com.adarsh.backend.feature.discount.domain.exception.CouponAlreadyExistsException;
import com.adarsh.backend.feature.discount.domain.exception.InvalidCouponException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCouponInteractor implements CreateCouponUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateCouponInteractor.class);

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;
    private final CouponCommandRepositoryPort couponCommandRepositoryPort;

    @Override
    @Transactional
    public CreateCouponResult execute(CreateCouponCommand command) {
        logger.info(DiscountInteractorLogConstants.CREATE_COUPON_REQUEST, command.code());

        if (command.code() == null || !command.code().matches("^[a-zA-Z0-9_-]+$")) {
            throw new InvalidCouponException(DiscountInteractorMessageConstants.COUPON_CODE_INVALID_FORMAT);
        }

        String normalizedCode = command.code().toUpperCase().trim();

        if (couponQueryRepositoryPort.existsByCode(normalizedCode)) {
            throw new CouponAlreadyExistsException(String.format(DiscountExceptionMessageConstants.COUPON_ALREADY_EXISTS, normalizedCode));
        }

        Coupon coupon = new Coupon.Builder().code(normalizedCode).discountType(command.discountType()).discountValue(command.discountValue()).minOrderValue(command.minOrderValue()).maxDiscountAmount(command.maxDiscountAmount()).expiryDate(command.expiryDate()).isActive(true).build();

        Coupon savedCoupon = couponCommandRepositoryPort.save(coupon);
        logger.info(DiscountInteractorLogConstants.CREATE_COUPON_SUCCESS, savedCoupon.getCode());

        return CreateCouponResult.fromDomain(savedCoupon);
    }
}