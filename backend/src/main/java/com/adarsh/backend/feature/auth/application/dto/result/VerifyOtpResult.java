package com.adarsh.backend.feature.auth.application.dto.result;

public record VerifyOtpResult(String accessToken, String refreshToken, VerifiedUserResult user) {

}
