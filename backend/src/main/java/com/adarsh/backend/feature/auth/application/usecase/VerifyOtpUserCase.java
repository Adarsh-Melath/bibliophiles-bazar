package com.adarsh.backend.feature.auth.application.usecase;

public interface VerifyOtpUserCase {
    void execute(String email, String enteredCode);
}
