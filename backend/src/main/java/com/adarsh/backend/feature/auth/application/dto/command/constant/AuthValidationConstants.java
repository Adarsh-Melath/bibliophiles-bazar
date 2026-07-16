package com.adarsh.backend.feature.auth.application.dto.command.constant;

public final class AuthValidationConstants {
    public static final String NAME_CANNOT_BE_NULL = "Name cannot be null";
    public static final String EMAIL_CANNOT_BE_NULL = "Email cannot be null";
    public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null";
    public static final String OTP_CANNOT_BE_NULL = "OTP cannot be null";
    public static final String INVALID_NAME = "Name must contain at least 2 characters";
    public static final String INVALID_EMAIL = "Email address is invalid";
    public static final String INVALID_PASSWORD = "Password must contain at least 8 characters";
    public static final String EMPTY_PASSWORD = "Password cannot be empty";
    public static final String INVALID_OTP = "OTP must be 6 digits";
    public static final String EMPTY_OTP = "OTP cannot be empty";

    private AuthValidationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
