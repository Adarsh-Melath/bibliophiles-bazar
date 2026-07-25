package com.adarsh.backend.feature.cart.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.cart.application.dto.command.UpdateCartItemCommand;
import com.adarsh.backend.feature.cart.application.dto.result.UpdateCartItemResult;
import com.adarsh.backend.feature.cart.application.interactor.constant.CartInteractorLogConstants;
import com.adarsh.backend.feature.cart.application.port.CartCommandRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.application.usecase.UpdateCartItemUseCase;
import com.adarsh.backend.feature.cart.domain.exception.CartItemNotFoundException;
import com.adarsh.backend.feature.cart.domain.exception.CartNotFoundException;
import com.adarsh.backend.feature.cart.domain.exception.InsufficientStockException;
import com.adarsh.backend.feature.cart.domain.exception.CartItemQuantityLimitExceededException;
import com.adarsh.backend.feature.cart.domain.exception.constant.CartExceptionMessageConstants;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.cart.domain.model.CartItem;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateCartItemInteractor implements UpdateCartItemUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCartItemInteractor.class);

    private final UserCommandRepository userCommandRepository;
    private final CartQueryRepositoryPort cartQueryRepositoryPort;
    private final CartCommandRepositoryPort cartCommandRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    @Transactional
    public UpdateCartItemResult updateCartItem(String userEmail, UpdateCartItemCommand command) {
        logger.info(CartInteractorLogConstants.UPDATE_CART_ITEM_REQUEST, command.cartItemId(), command.quantity(), userEmail);

        User user = userCommandRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(CartInteractorLogConstants.UPDATE_CART_ITEM_USER_FOUND, user.getId());

        Cart userCart = cartQueryRepositoryPort.findByUserId(user.getId()).orElseThrow(() -> new CartNotFoundException(CartExceptionMessageConstants.CART_NOT_FOUND));
        logger.debug(CartInteractorLogConstants.UPDATE_CART_ITEM_CART_FOUND, userCart.getId(), user.getId());

        CartItem targetItem = null;
        for (CartItem item : userCart.getItems()) {
            if (Objects.equals(item.getId(), command.cartItemId())) {
                targetItem = item;
                break;
            }
        }

        if (targetItem == null) {
            throw new CartItemNotFoundException(CartExceptionMessageConstants.CART_ITEM_NOT_FOUND);
        }

        int requestedQuantity = getRequestedQuantity(command);

        // Check book details and stock levels
        Book book = bookQueryRepositoryPort.findById(targetItem.getBookId()).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));

        if (requestedQuantity > book.getStock()) {
            throw new InsufficientStockException(String.format(CartExceptionMessageConstants.INSUFFICIENT_STOCK, requestedQuantity, book.getStock()));
        }

        targetItem.updateQuantity(requestedQuantity);
        cartCommandRepositoryPort.save(userCart);

        logger.info(CartInteractorLogConstants.UPDATE_CART_ITEM_SAVED, targetItem.getId(), requestedQuantity);

        return UpdateCartItemResult.fromDomain(targetItem);
    }

    private static int getRequestedQuantity(UpdateCartItemCommand command) {
        int requestedQuantity = command.quantity();

        // Double check quantities and constraints
        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException(CartExceptionMessageConstants.INVALID_QUANTITY);
        }
        if (requestedQuantity > CartExceptionMessageConstants.MAX_QUANTITY_LIMIT) {
            throw new CartItemQuantityLimitExceededException(String.format(CartExceptionMessageConstants.MAX_QUANTITY_EXCEEDED, CartExceptionMessageConstants.MAX_QUANTITY_LIMIT));
        }
        return requestedQuantity;
    }
}
