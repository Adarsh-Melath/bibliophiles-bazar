package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.command.ReturnSpecificOrderItemCommand;

public interface ReturnSpecificOrderItemUseCase {
    void execute(String email, Long orderId, Long orderItemId, ReturnSpecificOrderItemCommand command);
}
