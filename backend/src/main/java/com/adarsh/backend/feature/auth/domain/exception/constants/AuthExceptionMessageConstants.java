package com.adarsh.backend.feature.auth.domain.exception.constants;

public final class AuthExceptionMessageConstants {
    public static final String EMAIL_ALREADY_EXISTS = "An account with this email already exists.";
    public static final String INVALID_CREDENTIALS = "Invalid email or password.";
    public static final String EMAIL_NOT_VERIFIED = "Please verify your email address.";
    public static final String USER_BLOCKED = "Your account has been suspended. Please contact support.";
    public static final String RESET_PASSWORD_EMAIL_RESPONSE = "If this email is registered, you will receive a password reset code.";
    public static final String OTP_NOT_FOUND = "OTP not found.";
    public static final String OTP_EXPIRED = "OTP has expired.";
    public static final String INVALID_OTP = "Invalid OTP.";
    public static final String REFRESH_TOKEN_NOT_FOUND = "Refresh token not found.";
    public static final String REFRESH_TOKEN_EXPIRED = "Refresh token has expired.";
    public static final String TOKEN_USER_NOT_FOUND = "User associated with the token was not found.";
    public static final String INVALID_CURRENT_PASSWORD = "Current password is incorrect.";

    private AuthExceptionMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
