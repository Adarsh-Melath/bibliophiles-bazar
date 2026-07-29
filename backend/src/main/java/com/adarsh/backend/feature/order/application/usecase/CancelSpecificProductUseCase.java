package com.adarsh.backend.feature.order.application.usecase;

public interface CancelSpecificProductUseCase {
    void execute(String email, Long orderId, Long orderProductId);
}
