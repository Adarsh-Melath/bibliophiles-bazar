package com.adarsh.backend.feature.auth.application.dto.command;

import com.adarsh.backend.feature.auth.application.dto.command.constant.AuthValidationConstants;

import java.util.Objects;

public record VerifyOtpCommand(String email, String code) {
    public VerifyOtpCommand {
        Objects.requireNonNull(email, AuthValidationConstants.EMAIL_CANNOT_BE_NULL);

        Objects.requireNonNull(code, AuthValidationConstants.OTP_CANNOT_BE_NULL);

        if (email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_EMAIL);
        }

        if (code.isBlank() || !code.matches("\\d{6}")) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_OTP);
        }
    }
}
