package com.adarsh.backend.shared.application;

public interface SendOtpUseCase {
    void sendOtp(String email,String code);
}
