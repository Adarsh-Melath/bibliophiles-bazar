package com.adarsh.backend.shared.application.port;

public interface EmailPort {

    void sendOtp(String email, String code);

    void sendVendorApplicationConfirmation(String name, String email);

    void sendVendorApprovalEmail(String name, String email, String tempPassword);

    void sendVendorRejectionEmail(String name, String email, String rejectionReason);

}
