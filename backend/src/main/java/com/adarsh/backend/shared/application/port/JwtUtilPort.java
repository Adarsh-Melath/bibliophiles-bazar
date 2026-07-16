package com.adarsh.backend.shared.application.port;

import io.jsonwebtoken.Claims;

public interface JwtUtilPort {
    String generateAccessToken(String email, String role);

    Claims extractClaims(String token);

    boolean isTokenValid(String token);
}
