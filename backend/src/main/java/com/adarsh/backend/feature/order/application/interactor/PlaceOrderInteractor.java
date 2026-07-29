package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookCommandRepositoryPort;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.cart.domain.exception.InsufficientStockException;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.cart.application.port.CartItemCommandRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.domain.exception.CartNotFoundException;
import com.adarsh.backend.feature.cart.domain.exception.constant.CartExceptionMessageConstants;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.cart.domain.model.CartItem;
import com.adarsh.backend.feature.order.application.dto.command.PlaceOrderCommand;
import com.adarsh.backend.feature.order.application.dto.result.PlaceOrderResult;
import com.adarsh.backend.feature.order.application.port.OrderCommandRepositoryPort;
import com.adarsh.backend.feature.order.application.usecase.PlaceOrderUseCase;
import com.adarsh.backend.feature.order.domain.model.*;
import com.adarsh.backend.feature.user.application.port.AddressRepositoryPort;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.AddressNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.Address;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adarsh.backend.feature.order.application.interactor.constant.OrderInteractorLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderInteractor implements PlaceOrderUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PlaceOrderInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final AddressRepositoryPort addressRepositoryPort;
    private final OrderCommandRepositoryPort orderCommandRepositoryPort;
    private final CartQueryRepositoryPort cartQueryRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final BookCommandRepositoryPort bookCommandRepositoryPort;
    private final CartItemCommandRepositoryPort cartItemCommandRepositoryPort;

    @Override
    @Transactional
    public PlaceOrderResult execute(String email, PlaceOrderCommand command) {
        logger.info(OrderInteractorLogConstants.PLACE_ORDER_REQUEST, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.PLACE_ORDER_USER_FOUND, user.getId());
        String orderNumber = OrderNumberGenerator.generate();
        Address address = addressRepositoryPort.findByIdAndUserId(command.addressId(), user.getId()).orElseThrow(() -> new AddressNotFoundException(UserExceptionMessageConstants.ADDRESS_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.PLACE_ORDER_ADDRESS_FOUND, address.getId());
        Cart cart = cartQueryRepositoryPort.findByUserId(user.getId()).orElseThrow(() -> new CartNotFoundException(CartExceptionMessageConstants.CART_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.PLACE_ORDER_CART_FOUND, cart.getId(), user.getId());
        Double subtotal = 0.0;

        for (CartItem item : cart.getItems()) {
            subtotal += item.getQuantity() * item.getUnitPrice();
        }
        Double grandTotal = subtotal;
        List<OrderItem> orderItemList = cart.getItems().stream().map(cartItem -> {
            Book book = bookQueryRepositoryPort.findById(cartItem.getBookId()).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));
            logger.debug(OrderInteractorLogConstants.PLACE_ORDER_BOOK_FOUND, book.getId());

            if (book.getStock() < cartItem.getQuantity()) {
                throw new InsufficientStockException(String.format(CartExceptionMessageConstants.INSUFFICIENT_STOCK, cartItem.getQuantity(), book.getStock()));
            }
            logger.debug(OrderInteractorLogConstants.PLACE_ORDER_STOCK_CHECK, book.getId(), cartItem.getQuantity());
            book.deductStock(cartItem.getQuantity());
            bookCommandRepositoryPort.save(book);
            logger.debug(OrderInteractorLogConstants.PLACE_ORDER_STOCK_DEDUCTED, book.getId(), book.getStock());

            return new OrderItem.Builder().bookId(cartItem.getBookId()).bookTitle(book.getTitle()).author(book.getAuthor()).isbn(book.getIsbn()).quantity(cartItem.getQuantity()).unitPrice(cartItem.getUnitPrice()).totalPrice(cartItem.getUnitPrice() * cartItem.getQuantity()).status(OrderItemStatus.ACTIVE).build();
        }).toList();

        Order order = new Order.Builder().orderNumber(orderNumber).customerId(user.getId()).addressSnapshot(address).paymentMethod(command.paymentMethod()).paymentStatus(PaymentStatus.PENDING).orderStatus(OrderStatus.CONFIRMED).subtotal(subtotal).grandTotal(grandTotal).orderItemList(orderItemList).build();

        Order savedOrder = orderCommandRepositoryPort.save(order);
        logger.info(OrderInteractorLogConstants.PLACE_ORDER_SAVED, savedOrder.getId(), savedOrder.getOrderNumber());
        cartItemCommandRepositoryPort.deleteByCartId(cart.getId());
        logger.info(OrderInteractorLogConstants.PLACE_ORDER_CART_CLEARED, cart.getId());

        return PlaceOrderResult.fromDomain(savedOrder);
    }
}
