package com.adarsh.backend.feature.auth.application.interactor.constant;

public final class AuthInteractorLogConstants {

    // Create User
    public static final String CREATE_USER_REQUEST =
            "Creating user with email={}";
    public static final String CREATE_USER_SUCCESS =
            "User created successfully with id={}";
    // Login
    public static final String LOGIN_REQUEST =
            "Authenticating user with email={}";
    public static final String LOGIN_SUCCESS =
            "User authenticated successfully with id={}";
    // OTP Generation
    public static final String GENERATE_OTP =
            "Generating OTP for email={}";
    public static final String OTP_GENERATED =
            "OTP generated successfully for email={}";
    // OTP Verification
    public static final String VERIFY_OTP_REQUEST =
            "Verifying OTP for email={}";
    public static final String VERIFY_OTP_VALIDATED =
            "OTP validated successfully for email={}";
    public static final String VERIFY_OTP_USER_ENABLED =
            "User enabled successfully with id={}";
    public static final String VERIFY_OTP_SUCCESS =
            "OTP verified successfully for email={}";
    // Forgot Password
    public static final String FORGOT_PASSWORD_REQUEST =
            "Processing forgot password request for email={}";
    public static final String FORGOT_PASSWORD_SUCCESS =
            "Password reset OTP sent successfully to email={}";
    // Verify Reset OTP
    public static final String VERIFY_RESET_OTP_REQUEST =
            "Verifying password reset OTP for email={}";
    public static final String VERIFY_RESET_OTP_SUCCESS =
            "Password reset OTP verified successfully for email={}";
    // Refresh Token
    public static final String REFRESH_ACCESS_TOKEN_REQUEST =
            "Refreshing access token";
    public static final String REFRESH_ACCESS_TOKEN_SUCCESS =
            "Access token refreshed successfully";
    // Reset Password
    public static final String RESET_PASSWORD_REQUEST =
            "Processing password reset request for token={}";
    public static final String RESET_PASSWORD_SUCCESS =
            "Password reset successfully for email={}";

    private AuthInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}