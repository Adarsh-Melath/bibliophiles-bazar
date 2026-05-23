package com.adarsh.backend.shared.application.dto;

public class VerifyOtpResult {
    private String accessToken;
    private String refreshToken;
    private VerifiedUserResult user;

    public VerifyOtpResult(String accessToken, String refreshToken, VerifiedUserResult user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public VerifiedUserResult getUser() {
        return user;
    }
}
