package com.adarsh.backend.feature.vendor.domain.exception;

public class ApplicationAlreadyExistException extends RuntimeException {
    public ApplicationAlreadyExistException(String message) {
        super(message);
    }
}
