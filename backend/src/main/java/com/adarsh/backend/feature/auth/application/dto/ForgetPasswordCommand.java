package com.adarsh.backend.feature.auth.application.dto;

import java.util.Objects;

public class ForgetPasswordCommand {
    private String email;

    public ForgetPasswordCommand(String email) {

        Objects.requireNonNull(email, "Email cannot be null");

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
