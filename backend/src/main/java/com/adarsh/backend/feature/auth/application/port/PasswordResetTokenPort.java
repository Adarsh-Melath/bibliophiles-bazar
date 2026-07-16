package com.adarsh.backend.feature.auth.application.port;

public interface PasswordResetTokenPort {
    // Generates a 15-minute JWT
    String generateResetToken(String email);

    // Validates the JWT and extracts the email. Throws exception if
    // expired/invalid.
    String extractEmailIfValid(String token);
}
