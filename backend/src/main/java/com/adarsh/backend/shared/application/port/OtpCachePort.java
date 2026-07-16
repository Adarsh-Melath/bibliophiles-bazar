package com.adarsh.backend.shared.application.port;

public interface OtpCachePort {
    String generateOtp(String email);

    boolean verifyOtp(String email, String code);
}
