package com.adarsh.backend.feature.auth.application.dto;

import java.util.Objects;

public class LoginCommand {
    private String email;
    private String password;

    public LoginCommand(String email, String password) {
        Objects.requireNonNull(email, "Email cannot be null");

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}