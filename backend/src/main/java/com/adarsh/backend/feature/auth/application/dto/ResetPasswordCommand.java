package com.adarsh.backend.feature.auth.application.dto;

import java.util.Objects;

public class ResetPasswordCommand {
    private String resetToken;

    private String password;

    public ResetPasswordCommand(String resetToken, String password) {
        Objects.requireNonNull(password, "Password cannot be null");

        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        this.resetToken = resetToken;
        this.password = password;
    }

    public String getResetToken() {
        return resetToken;
    }

    public String getPassword() {
        return password;
    }
}
