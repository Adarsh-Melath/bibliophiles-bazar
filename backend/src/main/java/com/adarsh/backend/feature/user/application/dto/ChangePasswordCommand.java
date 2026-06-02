package com.adarsh.backend.feature.user.application.dto;

import java.util.Objects;

public class ChangePasswordCommand {
    private String currentPassword;
    private String newPassword;

    public ChangePasswordCommand(String currentPassword,String newPassword){
        Objects.requireNonNull(currentPassword,"Password cannot be null");
        Objects.requireNonNull(newPassword,"Password cannot be null");

         if (newPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        this.currentPassword=currentPassword;
        this.newPassword=newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
