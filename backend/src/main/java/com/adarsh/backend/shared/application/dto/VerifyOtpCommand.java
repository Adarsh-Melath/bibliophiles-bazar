package com.adarsh.backend.shared.application.dto;

import java.util.Objects;

public class VerifyOtpCommand {
    private String email;
    private String code;

    public VerifyOtpCommand(String email, String code) {
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(code, "Otp cannot be null");

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.email=email;
        this.code=code;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }
}
