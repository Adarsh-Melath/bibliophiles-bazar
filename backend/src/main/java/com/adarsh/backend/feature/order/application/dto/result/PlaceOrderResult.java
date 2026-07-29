package com.adarsh.backend.feature.order.application.dto.result;

import com.adarsh.backend.feature.order.domain.model.*;
import com.adarsh.backend.feature.user.domain.model.Address;

import java.time.LocalDateTime;
import java.util.List;

public record PlaceOrderResult(Long id, String OrderNumber, Long customerId,
                               Address addressSnapshot, List<OrderItem> orderItemList,
                               PaymentMethod paymentMethod, PaymentStatus paymentStatus,
                               OrderStatus orderStatus, Double subtotal, Double grandTotal,
                               LocalDateTime createdAt) {
    public static PlaceOrderResult fromDomain(Order order) {
        return new PlaceOrderResult(order.getId(), order.getOrderNumber(), order.getCustomerId(), order.getAddressSnapshot(), order.getOrderItemList(), order.getPaymentMethod(), order.getPaymentStatus(), order.getOrderStatus(), order.getSubtotal(), order.getGrandTotal(), order.getCreatedAt());
    }
}
