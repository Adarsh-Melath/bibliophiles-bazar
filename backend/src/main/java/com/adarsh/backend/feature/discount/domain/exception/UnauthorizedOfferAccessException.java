package com.adarsh.backend.feature.discount.domain.exception;

public class UnauthorizedOfferAccessException extends RuntimeException {
    public UnauthorizedOfferAccessException(String message) {
        super(message);
    }
}
