package com.adarsh.backend.shared.application.port;

public interface OtpRepositoryPort {
    void saveOtp(String email, String otp);

    String getLatestOtpByEmail(String email);

    void deleteOtpByEmail(String email);
}