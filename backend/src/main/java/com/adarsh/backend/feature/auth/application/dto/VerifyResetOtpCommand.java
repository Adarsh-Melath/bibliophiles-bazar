package com.adarsh.backend.feature.auth.application.dto;

import java.util.Objects;

public class VerifyResetOtpCommand {
    private String email;
    private String code;

    public VerifyResetOtpCommand(String email, String code) {
        Objects.requireNonNull(email, "Email cannot be null");

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
        this.code = code;
    }
    
    public String getEmail(){
        return email;
    }

    public String getCode() {
        return code;
    }
}
