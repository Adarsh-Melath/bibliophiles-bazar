package com.adarsh.backend.feature.cart.domain.exception;

public class CartItemQuantityLimitExceededException extends RuntimeException {
    public CartItemQuantityLimitExceededException(String message) {
        super(message);
    }
}
