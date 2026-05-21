package com.adarsh.backend.shared.application.usecase;

public interface SendOtpUseCase {
    void execute(String email,String code);
}
