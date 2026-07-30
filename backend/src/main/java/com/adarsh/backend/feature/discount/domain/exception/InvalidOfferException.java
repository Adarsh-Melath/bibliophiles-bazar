package com.adarsh.backend.feature.discount.domain.exception;

public class InvalidOfferException extends RuntimeException {
    public InvalidOfferException(String message) {
        super(message);
    }
}
