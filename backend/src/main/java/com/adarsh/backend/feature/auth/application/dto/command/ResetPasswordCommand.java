package com.adarsh.backend.feature.auth.application.dto.command;

import com.adarsh.backend.feature.auth.application.dto.command.constant.AuthValidationConstants;

import java.util.Objects;

public record ResetPasswordCommand(String resetToken, String password) {
    public ResetPasswordCommand{
        Objects.requireNonNull(password, AuthValidationConstants.PASSWORD_CANNOT_BE_NULL);

        if (password.length() < 8) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_PASSWORD);
        }
    }
}
