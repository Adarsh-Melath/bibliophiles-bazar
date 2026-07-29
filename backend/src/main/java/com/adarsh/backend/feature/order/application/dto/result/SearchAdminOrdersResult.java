package com.adarsh.backend.feature.order.application.dto.result;

import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.PaymentMethod;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;

import java.time.LocalDateTime;

public record SearchAdminOrdersResult(
        Long id,
        String orderNumber,
        Long customerId,
        PaymentStatus paymentStatus,
        PaymentMethod paymentMethod,
        OrderStatus orderStatus,
        Double subtotal,
        Double grandTotal,
        LocalDateTime createdAt
) {
    public static SearchAdminOrdersResult fromDomain(Order order) {
        return new SearchAdminOrdersResult(
                order.getId(),
                order.getOrderNumber(),
                order.getCustomerId(),
                order.getPaymentStatus(),
                order.getPaymentMethod(),
                order.getOrderStatus(),
                order.getSubtotal(),
                order.getGrandTotal(),
                order.getCreatedAt()
        );
    }
}
