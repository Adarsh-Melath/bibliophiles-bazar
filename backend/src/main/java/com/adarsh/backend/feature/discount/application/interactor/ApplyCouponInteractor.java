package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.discount.application.dto.command.ApplyCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorMessageConstants;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.ApplyCouponUseCase;
import com.adarsh.backend.feature.discount.domain.exception.CouponNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.InvalidCouponException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyCouponInteractor implements ApplyCouponUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ApplyCouponInteractor.class);

    private final UserQueryRepository userQueryRepository;
    private final CartQueryRepositoryPort cartQueryRepositoryPort;
    private final CouponQueryRepositoryPort couponQueryRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public ApplyCouponResult execute(String email, ApplyCouponCommand command) {
        logger.info(DiscountInteractorLogConstants.APPLY_COUPON_REQUEST, command.couponCode(), email);

        User user = userQueryRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Cart cart = cartQueryRepositoryPort.findByUserId(user.getId())
                .orElseThrow(() -> new InvalidCouponException(DiscountInteractorMessageConstants.EMPTY_CART_COUPON_ERROR));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new InvalidCouponException(DiscountInteractorMessageConstants.EMPTY_CART_COUPON_ERROR);
        }

        double subtotal = cart.getItems().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();

        String normalizedCode = command.couponCode().toUpperCase().trim();
        Coupon coupon = couponQueryRepositoryPort.findByCode(normalizedCode)
                .orElseThrow(() -> new CouponNotFoundException(String.format(DiscountExceptionMessageConstants.COUPON_NOT_FOUND, normalizedCode)));

        if (!coupon.isApplicable(subtotal)) {
            throw new InvalidCouponException(DiscountInteractorMessageConstants.INAPPLICABLE_COUPON_ERROR);
        }

        double discountAmount = coupon.calculateDiscount(subtotal);
        double finalTotal = Math.max(0.0, subtotal - discountAmount);

        logger.info(DiscountInteractorLogConstants.APPLY_COUPON_SUCCESS, normalizedCode, email);

        return new ApplyCouponResult(normalizedCode, subtotal, discountAmount, finalTotal, DiscountInteractorMessageConstants.COUPON_APPLY_SUCCESS);
    }
}
