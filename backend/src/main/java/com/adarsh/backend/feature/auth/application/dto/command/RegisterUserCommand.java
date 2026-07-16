package com.adarsh.backend.feature.auth.application.dto.command;

import com.adarsh.backend.feature.auth.application.dto.command.constant.AuthValidationConstants;

import java.util.Objects;

public record RegisterUserCommand(String name, String email, String password) {
    public RegisterUserCommand {
        Objects.requireNonNull(name, AuthValidationConstants.NAME_CANNOT_BE_NULL);
        Objects.requireNonNull(email, AuthValidationConstants.EMAIL_CANNOT_BE_NULL);
        Objects.requireNonNull(password, AuthValidationConstants.PASSWORD_CANNOT_BE_NULL);

        if (name.isBlank() || name.length() < 2) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_NAME);
        }
        if (email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_EMAIL);
        }
        if (password.isBlank() || password.length() < 8) {
            throw new IllegalArgumentException(
                    AuthValidationConstants.INVALID_PASSWORD);
        }
    }
}
