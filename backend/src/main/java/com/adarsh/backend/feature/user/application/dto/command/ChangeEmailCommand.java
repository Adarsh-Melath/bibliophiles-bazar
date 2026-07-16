package com.adarsh.backend.feature.user.application.dto.command;

import com.adarsh.backend.feature.user.application.dto.command.constant.UserValidationConstants;

import java.util.Objects;

public record ChangeEmailCommand(String email) {
    public ChangeEmailCommand {
        Objects.requireNonNull(email, UserValidationConstants.EMAIL_CANNOT_BE_NULL);
        if (!email.contains("@")) {
            throw new IllegalArgumentException(UserValidationConstants.INVALID_EMAIL);
        }
    }
}
