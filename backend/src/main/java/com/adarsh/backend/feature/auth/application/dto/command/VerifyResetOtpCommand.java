package com.adarsh.backend.feature.auth.application.dto.command;

import com.adarsh.backend.feature.auth.application.dto.command.constant.AuthValidationConstants;

import java.util.Objects;

public record VerifyResetOtpCommand(String email, String code) {

    public VerifyResetOtpCommand{
        Objects.requireNonNull(email, AuthValidationConstants.EMAIL_CANNOT_BE_NULL);

        Objects.requireNonNull(code, AuthValidationConstants.OTP_CANNOT_BE_NULL);

        if (code.isBlank()) {
            throw new IllegalArgumentException(AuthValidationConstants.EMPTY_OTP);
        }

        if (code.length() != 6) {
            throw new IllegalArgumentException(AuthValidationConstants.INVALID_OTP);
        }
    }
}
