package com.adarsh.backend.feature.order.application.dto.result;

import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderItem;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.PaymentMethod;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
import com.adarsh.backend.feature.user.domain.model.Address;

import java.time.LocalDateTime;
import java.util.List;

public record GetOrderDetailsResult(Long id, String orderNumber, Long customerId,
                                    Address addressSnapshot, List<OrderItem> orderItemList,
                                    PaymentMethod paymentMethod, PaymentStatus paymentStatus,
                                    OrderStatus orderStatus, Double subtotal, Double grandTotal,
                                    LocalDateTime createdAt) {
    public static GetOrderDetailsResult fromDomain(Order order) {
        return new GetOrderDetailsResult(order.getId(), order.getOrderNumber(), order.getCustomerId(), order.getAddressSnapshot(), order.getOrderItemList(), order.getPaymentMethod(), order.getPaymentStatus(), order.getOrderStatus(), order.getSubtotal(), order.getGrandTotal(), order.getCreatedAt());
    }
}