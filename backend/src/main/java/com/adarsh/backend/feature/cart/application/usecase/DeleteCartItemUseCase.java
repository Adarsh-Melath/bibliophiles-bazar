package com.adarsh.backend.feature.cart.application.usecase;

public interface DeleteCartItemUseCase {
    void execute(String email, Long cartItemId);
}
