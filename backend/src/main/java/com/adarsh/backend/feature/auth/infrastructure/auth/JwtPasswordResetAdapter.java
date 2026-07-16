package com.adarsh.backend.feature.auth.infrastructure.auth;

import com.adarsh.backend.feature.auth.application.port.PasswordResetTokenPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtPasswordResetAdapter implements PasswordResetTokenPort {
    private static final long EXPIRATION_TIME = (long) 15 * 60 * 1000; // 15 minutes in milliseconds
    @Value("${app.jwt.secret}")
    private String secretKey;

    // Helper method to generate a secure signing key
    private SecretKey getSignedKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public String generateResetToken(String email) {
        return Jwts.builder()
                   .subject(email).claim("scope", "RESET_PASSWORD")
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis()
                                                + EXPIRATION_TIME))
                   .signWith(getSignedKey()).compact();
    }

    @Override
    public String extractEmailIfValid(String token) {
        try {
            Claims claims = Jwts.parser()
                                .verifyWith(
                                        getSignedKey())
                                .build()
                                .parseSignedClaims(token)
                                .getPayload();

            if (!"RESET_PASSWORD".equals(claims.get("scope"))) {
                throw new IllegalArgumentException("Invalid token scope. This is not a reset token.");
            }

            return claims.getSubject();

        } catch (JwtException ex) {
            throw new IllegalArgumentException("Invalid or expired password reset token.", ex);
        }
    }
}
