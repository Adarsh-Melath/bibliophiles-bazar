package com.adarsh.backend.feature.order.application.dto.command;

import com.adarsh.backend.feature.order.domain.model.PaymentMethod;

public record PlaceOrderCommand(Long addressId, PaymentMethod paymentMethod) {
}
