package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.order.application.dto.command.ReturnOrderCommand;
import com.adarsh.backend.feature.order.application.port.OrderCommandRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.usecase.ReturnOrderUseCase;
import com.adarsh.backend.feature.order.domain.exception.OrderNotFoundException;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderItem;
import com.adarsh.backend.feature.order.domain.model.OrderItemStatus;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.returnrequest.application.port.ReturnRequestCommandRepositoryPort;
import com.adarsh.backend.feature.returnrequest.domain.model.ReturnRequest;
import com.adarsh.backend.feature.returnrequest.domain.model.ReturnStatus;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adarsh.backend.feature.order.domain.exception.InvalidOrderStateException;
import com.adarsh.backend.feature.order.domain.exception.constant.OrderExceptionMessageConstants;
import com.adarsh.backend.feature.order.application.interactor.constant.OrderInteractorLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ReturnOrderInteractor implements ReturnOrderUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ReturnOrderInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final OrderQueryRepositoryPort orderQueryRepositoryPort;
    private final OrderCommandRepositoryPort orderCommandRepositoryPort;
    private final ReturnRequestCommandRepositoryPort returnRequestCommandRepositoryPort;

    @Override
    @Transactional
    public void execute(String email, Long orderId, ReturnOrderCommand command) {
        logger.info(OrderInteractorLogConstants.RETURN_ORDER_REQUEST, orderId, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.RETURN_ORDER_USER_FOUND, user.getId());
        Order order = orderQueryRepositoryPort.findByUserIdAndOrderId(user.getId(), orderId).orElseThrow(() -> new OrderNotFoundException(String.format(OrderExceptionMessageConstants.ORDER_NOT_FOUND_FOR_USER, user.getId(), orderId)));
        logger.debug(OrderInteractorLogConstants.RETURN_ORDER_FOUND, order.getId());
        if (!order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new InvalidOrderStateException(OrderExceptionMessageConstants.RETURNS_ONLY_FOR_DELIVERED);
        }
        order.returnOrderRequest();
        orderCommandRepositoryPort.save(order);
        logger.info(OrderInteractorLogConstants.RETURN_ORDER_SUCCESS, order.getId());

        for (OrderItem item : order.getOrderItemList()) {
            if (item.getStatus() == OrderItemStatus.RETURN_REQUESTED) {
                ReturnRequest returnRequest = new ReturnRequest.Builder().orderId(order.getId()).orderItemId(item.getId()).customerId(user.getId()).reason(command.reason()).comments("Full order return").status(ReturnStatus.PENDING_APPROVAL).build();
                returnRequestCommandRepositoryPort.save(returnRequest);
            }
        }
    }
}
