package com.adarsh.backend.shared.application.port;

import java.util.Optional;

import com.adarsh.backend.shared.domain.OtpToken;


public interface OtpTokenRepository {
    OtpToken save(OtpToken otpToken);

    Optional<OtpToken> getLatestOtpByEmail(String email);

    void deleteOtpByEmail(String email);
}