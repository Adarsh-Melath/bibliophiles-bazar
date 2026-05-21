package com.adarsh.backend.feature.auth.application.dto;

import java.util.Objects;

public class CreateUserCommand {
    private String name;
    private String email;
    private String password;

    public CreateUserCommand(String name, String email, String password) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(password, "Password cannot be null");

        if (name.isBlank() || name.length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
