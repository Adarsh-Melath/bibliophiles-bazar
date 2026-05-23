package com.adarsh.backend.shared.application.port;

import io.jsonwebtoken.Claims;

public interface JwtUtilPort {
    public String generateAccessToken(String email, String role);

    public Claims extractClaims(String token);

    public boolean isTokenValid(String token);
}
