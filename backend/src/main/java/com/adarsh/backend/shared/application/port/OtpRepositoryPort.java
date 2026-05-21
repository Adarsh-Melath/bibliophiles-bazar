package com.adarsh.backend.shared.application.port;

public interface OtpRepositoryPort {
    void saveOtp(String email, String otp);

    String getOtpByEmail(String email);
}