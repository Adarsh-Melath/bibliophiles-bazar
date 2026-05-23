package com.adarsh.backend.feature.auth.application.dto;

public class LoginResult {
    private String accessToken;
    private String refreshToken;
    private LoginUserResult user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LoginUserResult getUser() {
        return user;
    }

    public void setUser(LoginUserResult user) {
        this.user = user;
    }
}