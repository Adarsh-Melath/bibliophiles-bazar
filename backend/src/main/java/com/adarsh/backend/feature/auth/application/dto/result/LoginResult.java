package com.adarsh.backend.feature.auth.application.dto.result;

public record LoginResult(
        String accessToken,
        String refreshToken,
        LoginUserResult user

) {

}