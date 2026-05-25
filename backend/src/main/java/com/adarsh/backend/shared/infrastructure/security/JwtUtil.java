package com.adarsh.backend.shared.infrastructure.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adarsh.backend.shared.application.port.JwtUtilPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil implements JwtUtilPort {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    @Override
    public String generateAccessToken(String email, String role) {
        long currentTimeMillis = System.currentTimeMillis();

        long expirationTime = currentTimeMillis + expiration;
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date()).expiration(new Date(expirationTime))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
