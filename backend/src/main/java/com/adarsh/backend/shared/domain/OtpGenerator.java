package com.adarsh.backend.shared.domain;

import java.security.SecureRandom;

public class OtpGenerator {
    // We use SecureRandom instead of Random for cryptographically strong OTPs
    private static final SecureRandom secureRandom = new SecureRandom();

    // Private constructor to prevent instantiation of utility class
    private OtpGenerator() {
    }

    public static String generate6DigitOtp() {
        int number = secureRandom.nextInt(999999);
        return String.format("%06d", number);
    }
}
