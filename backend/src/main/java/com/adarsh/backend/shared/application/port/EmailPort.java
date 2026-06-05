package com.adarsh.backend.shared.application.port;

public interface EmailPort {

    void sendOtp(String email, String code);

    void sendVendorApplicationConfirmation(String name, String email);
     
}
