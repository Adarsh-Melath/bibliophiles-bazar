package com.adarsh.backend.feature.auth.presentation.constant;

public final class AuthControllerConstants {

    public static final String BASE_PATH = "/api/v1/auth";

    public static final String REGISTER_PATH = "/register";
    public static final String VERIFY_OTP_PATH = "/verify-otp";
    public static final String LOGIN_PATH = "/login";
    public static final String REFRESH_PATH = "/refresh";
    public static final String FORGOT_PASSWORD_PATH = "/forget-password";
    public static final String VERIFY_RESET_OTP_PATH = "/verify-reset-otp";
    public static final String RESET_PASSWORD_PATH = "/reset-password";

    private AuthControllerConstants() {
        throw new UnsupportedOperationException("Utility class");
    }
}