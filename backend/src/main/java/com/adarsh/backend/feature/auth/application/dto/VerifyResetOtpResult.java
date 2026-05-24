package com.adarsh.backend.feature.auth.application.dto;

public class VerifyResetOtpResult {
    String resetToken;

    public VerifyResetOtpResult(String resetToken) {
        this.resetToken = resetToken;
    }

}
