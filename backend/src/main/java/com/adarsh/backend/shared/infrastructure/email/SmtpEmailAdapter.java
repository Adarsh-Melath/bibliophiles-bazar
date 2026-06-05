package com.adarsh.backend.shared.infrastructure.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.adarsh.backend.shared.application.port.EmailPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmtpEmailAdapter implements EmailPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("The Bibliophile's Bazar -  Email verification");
        message.setText("Your OTP for email verification is: " + otp);
        mailSender.send(message);
    }

    @Override
    public void sendVendorApplicationConfirmation(String name, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Bibliophile's Bazar — Application Received");
        message.setText("Hi " + name + ",\n\nThank you for applying to become a vendor on Bibliophile's Bazar. "
                + "We have received your application and will review it shortly.\n\nThe Bibliophile's Bazar Team");

        mailSender.send(message);
    }
}
