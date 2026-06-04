package com.adarsh.backend.shared.domain.exception;

public class AddressNotFoundException implements RuntimeException {

    public AddressNotFoundException(String message) {
        super(message);
    }
}
