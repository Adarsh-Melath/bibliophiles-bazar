package com.adarsh.backend.feature.cart.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.cart.application.dto.command.AddCartItemCommand;
import com.adarsh.backend.feature.cart.application.dto.result.AddCartItemResult;
import com.adarsh.backend.feature.cart.application.interactor.constant.CartInteractorLogConstants;
import com.adarsh.backend.feature.cart.application.port.CartCommandRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartItemQueryRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.application.usecase.AddCartItemUseCase;
import com.adarsh.backend.feature.cart.domain.exception.constant.CartExceptionMessageConstants;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.cart.domain.model.CartItem;
import com.adarsh.backend.feature.cart.domain.exception.InsufficientStockException;
import com.adarsh.backend.feature.cart.domain.exception.CartItemQuantityLimitExceededException;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddCartItemInteractor implements AddCartItemUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddCartItemInteractor.class);

    private final UserCommandRepository userCommandRepository;
    private final CartCommandRepositoryPort cartCommandRepository;
    private final CartQueryRepositoryPort cartQueryRepositoryPort;
    private final CartItemQueryRepositoryPort cartItemQueryRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    public AddCartItemResult execute(String email, AddCartItemCommand command) {
        logger.info(CartInteractorLogConstants.ADD_CART_ITEM_REQUEST, email, command.slug());

        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(CartInteractorLogConstants.ADD_CART_ITEM_USER_FOUND, user.getId());

        Book book = bookQueryRepositoryPort.findBySlug(command.slug()).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));
        logger.debug(CartInteractorLogConstants.ADD_CART_ITEM_BOOK_FOUND, book.getId());

        Cart cart = cartQueryRepositoryPort.findByUserId(user.getId()).orElseGet(() -> {
            Cart newCart = new Cart.Builder().userId(user.getId()).build();
            Cart savedCart = cartCommandRepository.save(newCart);
            logger.debug(CartInteractorLogConstants.ADD_CART_ITEM_CART_CREATED, savedCart.getId(), user.getId());
            return savedCart;
        });

        if (cart.getId() != null) {
            logger.debug(CartInteractorLogConstants.ADD_CART_ITEM_CART_FOUND, cart.getId(), user.getId());
        }

        Optional<CartItem> existingCartItemOpt = cartItemQueryRepositoryPort.findByCartIdAndBookId(cart.getId(), book.getId());
        int requestedQuantity = command.quantity();
        int targetQuantity = existingCartItemOpt.map(item -> item.getQuantity() + requestedQuantity).orElse(requestedQuantity);

        if (targetQuantity > CartExceptionMessageConstants.MAX_QUANTITY_LIMIT) {
            throw new CartItemQuantityLimitExceededException(String.format(CartExceptionMessageConstants.MAX_QUANTITY_EXCEEDED, CartExceptionMessageConstants.MAX_QUANTITY_LIMIT));
        }

        if (targetQuantity > book.getStock()) {
            throw new InsufficientStockException(String.format(CartExceptionMessageConstants.INSUFFICIENT_STOCK, targetQuantity, book.getStock()));
        }

        CartItem cartItem;
        if (existingCartItemOpt.isPresent()) {
            cartItem = existingCartItemOpt.get();
            cartItem.updateQuantity(targetQuantity);
        } else {
            cartItem = new CartItem.Builder().cartId(cart.getId()).bookId(book.getId()).quantity(requestedQuantity).unitPrice(book.getPrice()).build();
            cart.addItem(cartItem);
        }
        Cart savedCart = cartCommandRepository.save(cart);

        logger.info(CartInteractorLogConstants.ADD_CART_ITEM_SAVED, cartItem.getId(), savedCart.getId());
        return AddCartItemResult.fromDomain(cartItem);
    }
}
