package com.adarsh.backend.feature.auth.application.dto.result;

public record RefreshAccessTokenResult(String accessToken, String refreshToken,
                                       RefreshAccessTokenUserResult user) {
}
