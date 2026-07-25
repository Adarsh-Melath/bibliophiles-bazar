package com.adarsh.backend.feature.cart.domain.exception;

public class CartAccessDeniedException extends RuntimeException {
    public CartAccessDeniedException(String message) {
        super(message);
    }
}
