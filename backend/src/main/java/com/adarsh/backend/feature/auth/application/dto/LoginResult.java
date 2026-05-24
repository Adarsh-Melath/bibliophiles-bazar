package com.adarsh.backend.feature.auth.application.dto;

public class LoginResult {
    private String accessToken;
    private String refreshToken;
    private LoginUserResult user;

    public LoginResult(String accessToken, String refreshToken, LoginUserResult user) {
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

    public LoginUserResult getUser() {
        return user;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUser(LoginUserResult user) {
        this.user = user;
    }

}