package com.adarsh.backend.feature.auth.presentation.constant;

public final class AuthCookieConstants {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    public static final String COOKIE_PATH = "/api";
    public static final long REFRESH_TOKEN_COOKIE_MAX_AGE = 7L * 24 * 60 * 60;

    public static final String SAME_SITE_POLICY = "Lax";

    private AuthCookieConstants() {
    }
}