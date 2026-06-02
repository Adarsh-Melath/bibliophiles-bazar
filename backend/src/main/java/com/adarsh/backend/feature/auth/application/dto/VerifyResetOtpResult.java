package com.adarsh.backend.feature.auth.application.dto;

public class VerifyResetOtpResult {
    private String resetToken;

    public VerifyResetOtpResult(String resetToken) {
        this.resetToken = resetToken;
    }

    public String  getResetToken(){
        return resetToken;
    }
}
