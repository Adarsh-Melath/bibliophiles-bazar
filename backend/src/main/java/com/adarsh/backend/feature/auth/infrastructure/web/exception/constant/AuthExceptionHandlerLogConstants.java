package com.adarsh.backend.feature.auth.infrastructure.web.exception.constant;

public final class AuthExceptionHandlerLogConstants {

    private AuthExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static final String REFRESH_TOKEN_EXPIRED = "Refresh token expired.";
    public static final String REFRESH_TOKEN_NOT_FOUND = "Refresh token not found.";
    public static final String INVALID_OTP = "Invalid OTP provided.";
    public static final String ERROR_RESPONSE_SENT = "Error response sent. Status: {}, Message: {}, Path: {}";
}