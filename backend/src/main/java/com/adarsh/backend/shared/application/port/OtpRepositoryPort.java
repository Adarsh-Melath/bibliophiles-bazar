package com.adarsh.backend.shared.application.port;

import java.util.Optional;

import com.adarsh.backend.feature.auth.domain.OtpToken;

public interface OtpRepositoryPort {
    void saveOtp(String email, String otp);

    Optional<OtpToken> getLatestOtpByEmail(String email);

    void deleteOtpByEmail(String email);
}