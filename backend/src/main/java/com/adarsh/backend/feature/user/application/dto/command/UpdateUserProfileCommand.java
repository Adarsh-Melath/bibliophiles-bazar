package com.adarsh.backend.feature.user.application.dto.command;

import com.adarsh.backend.feature.user.application.dto.command.constant.UserValidationConstants;

public record UpdateUserProfileCommand(String name, String phone, String profileImage) {

    public UpdateUserProfileCommand {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(UserValidationConstants.NAME_CANNOT_BE_NULL);
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException(UserValidationConstants.INVALID_NAME);
        }

        if (phone != null && !phone.trim().isEmpty()) {
            if (!phone.matches("^\\+?[0-9]{7,15}$")) {
                throw new IllegalArgumentException(UserValidationConstants.INVALID_PHONE);
            }
        }
    }
}
