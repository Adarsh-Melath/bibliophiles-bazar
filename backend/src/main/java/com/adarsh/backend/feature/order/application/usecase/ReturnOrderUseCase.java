package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.command.ReturnOrderCommand;

public interface ReturnOrderUseCase {
    void execute(String email, Long orderId, ReturnOrderCommand command);
}
