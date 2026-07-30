package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorMessageConstants;
import com.adarsh.backend.feature.discount.application.usecase.RemoveCouponUseCase;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveCouponInteractor implements RemoveCouponUseCase {

    private final UserQueryRepository userQueryRepository;
    private final CartQueryRepositoryPort cartQueryRepositoryPort;

    @Override
    public ApplyCouponResult execute(String email) {
        User user = userQueryRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Cart cart = cartQueryRepositoryPort.findByUserId(user.getId()).orElse(null);

        double subtotal = 0.0;
        if (cart != null && cart.getItems() != null) {
            subtotal = cart.getItems().stream()
                    .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                    .sum();
        }

        return new ApplyCouponResult(null, subtotal, 0.0, subtotal, DiscountInteractorMessageConstants.COUPON_REMOVE_SUCCESS);
    }
}
