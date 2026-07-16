package com.adarsh.backend.feature.auth.presentation.constant;

public final class AuthControllerLogMessageConstants {

    // Registration
    public static final String REGISTER_REQUEST =
            "Received user registration request for email={}";

    public static final String REGISTER_SUCCESS =
            "User registration successful for email={}";

    // OTP Verification
    public static final String VERIFY_OTP_REQUEST =
            "Received OTP verification request for email={}";

    public static final String VERIFY_OTP_SUCCESS =
            "OTP verification completed for email={}";

    // Login
    public static final String LOGIN_REQUEST =
            "Received login request for email={}";

    public static final String LOGIN_SUCCESS =
            "Login successful for email={}";

    // Refresh Token
    public static final String REFRESH_TOKEN_REQUEST =
            "Received refresh token request.";

    public static final String REFRESH_TOKEN_SUCCESS =
            "Access token refreshed successfully.";

    public static final String REFRESH_TOKEN_MISSING =
            "Refresh token is missing in the request cookies.";

    // Forgot Password
    public static final String FORGOT_PASSWORD_REQUEST =
            "Received forgot password request for email={}";

    public static final String FORGOT_PASSWORD_SUCCESS =
            "Password reset OTP sent successfully for email={}";

    // Verify Reset OTP
    public static final String VERIFY_RESET_OTP_REQUEST =
            "Received verify reset OTP request for email={}";

    public static final String VERIFY_RESET_OTP_SUCCESS =
            "Password reset OTP verification completed for email={}";

    // Reset Password
    public static final String RESET_PASSWORD_REQUEST =
            "Received reset password request for email={}";

    public static final String RESET_PASSWORD_SUCCESS =
            "Password reset completed successfully";

    private AuthControllerLogMessageConstants() {
        // Private constructor to prevent instantiation
    }
}