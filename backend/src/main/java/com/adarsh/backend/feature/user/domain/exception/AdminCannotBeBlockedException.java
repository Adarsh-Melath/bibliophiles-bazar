package com.adarsh.backend.feature.user.domain.exception;

public class AdminCannotBeBlockedException extends RuntimeException {
    public AdminCannotBeBlockedException(String message) {
        super(message);
    }
}
