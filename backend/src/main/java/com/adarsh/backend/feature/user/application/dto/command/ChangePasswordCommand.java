package com.adarsh.backend.feature.user.application.dto.command;

import com.adarsh.backend.feature.user.application.dto.command.constant.UserValidationConstants;

import java.util.Objects;

public record ChangePasswordCommand(String currentPassword, String newPassword) {
    public ChangePasswordCommand {
        Objects.requireNonNull(currentPassword, UserValidationConstants.PASSWORD_CANNOT_BE_NULL);
        Objects.requireNonNull(newPassword, UserValidationConstants.PASSWORD_CANNOT_BE_NULL);

        if (newPassword.length() < 8) {
            throw new IllegalArgumentException(UserValidationConstants.INVALID_PASSWORD);
        }
    }
}
