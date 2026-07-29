package com.adarsh.backend.feature.order.application.usecase;

public interface CancelOrderUseCase {
    void execute(String email, Long orderId);
}
