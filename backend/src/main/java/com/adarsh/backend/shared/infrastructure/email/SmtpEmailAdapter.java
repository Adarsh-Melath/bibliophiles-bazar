package com.adarsh.backend.shared.infrastructure.email;

import com.adarsh.backend.shared.application.port.EmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmtpEmailAdapter implements EmailPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("The Bibliophile's Bazaar -  Email verification");
        message.setText("Your OTP for email verification is: " + otp);
        mailSender.send(message);
    }

    @Override
    public void sendVendorApplicationConfirmation(String name, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Bibliophile's Bazaar — Application Received");
        message.setText("Hi " + name + ",\n\nThank you for applying to become a vendor on " + "Bibliophile's Bazaar. " + "We have received your application and will review it " + "shortly.\n\nThe Bibliophile's Bazaar Team");

        mailSender.send(message);
    }

    @Override
    public void sendVendorApprovalEmail(String name, String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Bibliophile's Bazaar — Vendor Account Created");
        message.setText("Hi " + name + ",\n\nYour vendor account has been created successfully. " + "You can now log in to your account using the following credentials:\n\nUsername: " + toEmail + "\nPassword: " + tempPassword + "\n\nThe Bibliophile's Bazaar Team");
        mailSender.send(message);
    }

    @Override
    public void sendVendorRejectionEmail(String name, String toEmail, String rejectionReason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Bibliophile's Bazaar — Vendor Application Update");
        message.setText("Hi " + name + ",\n\nWe regret to inform you that your vendor application" + " has been rejected. " + "Reason: " + rejectionReason + "\n\nThank you for your " + "interest in becoming a vendor on Bibliophile's Bazaar.\n\nThe Bibliophile's Bazaar Team");
        mailSender.send(message);
    }
}
