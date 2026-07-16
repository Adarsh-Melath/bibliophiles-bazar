package com.adarsh.backend.feature.cart.application.interactor;

import com.adarsh.backend.feature.cart.application.interactor.constant.CartInteractorLogConstants;
import com.adarsh.backend.feature.cart.application.port.CartItemCommandRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartItemQueryRepositoryPort;
import com.adarsh.backend.feature.cart.application.usecase.DeleteCartItemUseCase;
import com.adarsh.backend.feature.cart.domain.exception.CartItemNotFoundException;
import com.adarsh.backend.feature.cart.domain.exception.constant.CartExceptionMessageConstants;
import com.adarsh.backend.feature.cart.domain.model.CartItem;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCartItemInteractor implements DeleteCartItemUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCartItemInteractor.class);

    private final UserCommandRepository userCommandRepository;
    private final CartItemQueryRepositoryPort cartItemQueryRepositoryPort;
    private final CartItemCommandRepositoryPort cartItemCommandRepositoryPort;

    @Override
    public void execute(String email, Long cartItemId) {
        logger.info(CartInteractorLogConstants.DELETE_CART_ITEM_REQUEST, cartItemId, email);

        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        CartItem item = cartItemQueryRepositoryPort.findByCartItemId(cartItemId).orElseThrow(() -> new CartItemNotFoundException(CartExceptionMessageConstants.CART_ITEM_NOT_FOUND));
        logger.debug(CartInteractorLogConstants.DELETE_CART_ITEM_FOUND, item.getId());

        cartItemCommandRepositoryPort.deleteByCartIdAndCartItemId(item.getCartId(), item.getId());

        logger.info(CartInteractorLogConstants.DELETE_CART_ITEM_DELETED, item.getId());
    }
}
