package com.adarsh.backend.shared.application.usecase;

public interface VerifyOtpUserCase {
    void execute(String email, String enteredCode);
}
