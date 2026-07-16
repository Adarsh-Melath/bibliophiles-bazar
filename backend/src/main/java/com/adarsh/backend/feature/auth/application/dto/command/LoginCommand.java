package com.adarsh.backend.feature.auth.application.dto.command;

import com.adarsh.backend.feature.auth.application.dto.command.constant.AuthValidationConstants;

import java.util.Objects;

public record LoginCommand(String email,String password) {
    public LoginCommand{
        Objects.requireNonNull(email, AuthValidationConstants.EMAIL_CANNOT_BE_NULL);
        Objects.requireNonNull(password, AuthValidationConstants.PASSWORD_CANNOT_BE_NULL);

        if (email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_EMAIL);
        }

        if (password.isBlank()) {
            throw new IllegalArgumentException(AuthValidationConstants.EMPTY_PASSWORD);
        }
    }
}