package com.adarsh.backend.shared.application.usecase;

public interface SendOtpUseCase {
    void sendOtp(String email,String code);
}
