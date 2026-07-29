package com.adarsh.backend.feature.order.application.dto.result;

import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;

import java.time.LocalDateTime;

public record SearchCustomerOrdersResult(Long id, String orderNumber, PaymentStatus paymentStatus,
                                         OrderStatus orderStatus, Double grandTotal,
                                         LocalDateTime createdAt) {
    public static SearchCustomerOrdersResult fromDomain(Order order) {
        return new SearchCustomerOrdersResult(order.getId(), order.getOrderNumber(), order.getPaymentStatus(), order.getOrderStatus(), order.getGrandTotal(), order.getCreatedAt());
    }
}