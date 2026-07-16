package com.adarsh.backend.feature.auth.application.dto.command;

import com.adarsh.backend.feature.auth.application.dto.command.constant.AuthValidationConstants;

import java.util.Objects;

public record ForgetPasswordCommand(String email) {
    public ForgetPasswordCommand {
        Objects.requireNonNull(email, AuthValidationConstants.EMAIL_CANNOT_BE_NULL);

        if (email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_EMAIL);
        }
    }
}
