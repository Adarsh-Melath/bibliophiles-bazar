package com.adarsh.backend.feature.user.domain.exception;

public class CannotBlockAdminUserException extends RuntimeException {
    public CannotBlockAdminUserException(String message) {
        super(message);
    }
}
