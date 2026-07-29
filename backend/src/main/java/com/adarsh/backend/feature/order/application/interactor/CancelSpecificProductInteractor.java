package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookCommandRepositoryPort;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.order.application.port.OrderCommandRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.usecase.CancelSpecificProductUseCase;
import com.adarsh.backend.feature.order.domain.exception.OrderNotFoundException;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderItem;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adarsh.backend.feature.order.domain.exception.constant.OrderExceptionMessageConstants;
import com.adarsh.backend.feature.order.application.interactor.constant.OrderInteractorLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class CancelSpecificProductInteractor implements CancelSpecificProductUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CancelSpecificProductInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final OrderQueryRepositoryPort orderQueryRepositoryPort;
    private final OrderCommandRepositoryPort orderCommandRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final BookCommandRepositoryPort bookCommandRepositoryPort;

    @Override
    @Transactional
    public void execute(String email, Long orderId, Long orderProductId) {
        logger.info(OrderInteractorLogConstants.CANCEL_PRODUCT_REQUEST, orderProductId, orderId, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.CANCEL_PRODUCT_USER_FOUND, user.getId());
        Order order = orderQueryRepositoryPort.findByUserIdAndOrderId(user.getId(), orderId).orElseThrow(() -> new OrderNotFoundException(OrderExceptionMessageConstants.ORDER_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.CANCEL_PRODUCT_ORDER_FOUND, order.getId());

        for (OrderItem orderItem : order.getOrderItemList()) {
            if (orderItem.getId().equals(orderProductId)) {
                orderItem.cancelOrderItem();
                Book book = bookQueryRepositoryPort.findById(orderItem.getBookId()).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));
                book.updateStock(orderItem.getQuantity());
                bookCommandRepositoryPort.save(book);
                logger.info(OrderInteractorLogConstants.CANCEL_PRODUCT_STOCK_RESTORED, book.getId(), orderItem.getQuantity());
                break;
            }
        }
        order.changeOrderStatus(OrderStatus.PARTIALLY_CANCELLED);
        orderCommandRepositoryPort.save(order);
        logger.info(OrderInteractorLogConstants.CANCEL_PRODUCT_SUCCESS, orderProductId, order.getId());
    }
}
