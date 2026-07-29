package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.command.PlaceOrderCommand;
import com.adarsh.backend.feature.order.application.dto.result.PlaceOrderResult;

public interface PlaceOrderUseCase {
    PlaceOrderResult execute(String email, PlaceOrderCommand command);
}
