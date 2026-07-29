package com.adarsh.backend.feature.order.application.usecase;

public interface GenerateInvoiceUseCase {
    byte[] execute(String email, Long orderId);
}
