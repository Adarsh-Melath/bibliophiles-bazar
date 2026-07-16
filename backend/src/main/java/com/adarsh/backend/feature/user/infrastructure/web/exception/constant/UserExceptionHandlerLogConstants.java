package com.adarsh.backend.feature.user.infrastructure.web.exception.constant;

public class UserExceptionHandlerLogConstants {

    private UserExceptionHandlerLogConstants() {
    }

    public static final String EXCEPTION_CAUGHT = "Exception caught in UserExceptionHandler: {}";
    public static final String CANNOT_BLOCK_ADMIN = "Attempting to block admin user. Exception: {}";
    public static final String INVALID_CREDENTIAL = "Invalid credentials provided. Exception: {}";
    public static final String USER_BLOCKED = "Attempt to access with blocked user account. Exception: {}";
    public static final String EMAIL_NOT_VERIFIED = "Attempt to access with unverified email. Exception: {}";
    public static final String USER_NOT_FOUND = "User not found in system. Exception: {}";
    public static final String PASSWORD_INCORRECT = "Incorrect password provided. Exception: {}";
    public static final String USER_ALREADY_EXIST = "User already exists in system. Exception: {}";
    public static final String ADDRESS_NOT_FOUND = "Address not found in system. Exception: {}";
    public static final String MEDIA_UPLOAD_FAILED = "Media upload failed. Exception: {}";
    public static final String ERROR_RESPONSE_SENT = "Error response sent. Status: {}, Message: {}, Path: {}";
}
