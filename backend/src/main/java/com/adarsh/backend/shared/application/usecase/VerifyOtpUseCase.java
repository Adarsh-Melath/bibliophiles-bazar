package com.adarsh.backend.shared.application.usecase;

public interface VerifyOtpUseCase {
    void execute(String email, String enteredCode);
}
