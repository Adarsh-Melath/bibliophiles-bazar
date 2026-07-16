package com.adarsh.backend.shared.infrastructure.security;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.shared.application.port.JwtUtilPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtilPort jwtUtilPort;
    private final UserCommandRepository userCommandRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String requestUri = request.getRequestURI();

            log.info("accessToken: {}", authHeader);
            log.info("Processing request to: {}", requestUri);

            // Check if Authorization header is missing or doesn't have Bearer prefix
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.debug("No valid Authorization header found for: {}", requestUri);
                filterChain.doFilter(request, response);
                return;
            }

            // Extract token from Bearer prefix
            String token = authHeader.substring(7);

            log.info("is accessToken valid : {}", jwtUtilPort.isTokenValid(token));
            // Validate token
            if (!jwtUtilPort.isTokenValid(token)) {
                log.info("Invalid JWT token for: {}", requestUri);
                filterChain.doFilter(request, response);
                return;
            }

            // Extract claims and set authentication
            var claims = jwtUtilPort.extractClaims(token);
            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            log.info("JWT token valid for email: {}, role: {}", email, role);

            userCommandRepository.findByEmail(email).ifPresent(user -> {
                if (user.isEnabled() && !user.isBlocked()) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.info("Authentication set successfully for: {}", email);
                } else {
                    log.info("User {} is disabled or blocked", email);
                }
            });

            if (userCommandRepository.findByEmail(email).isEmpty()) {
                log.info("User not found for email: {}", email);
            }

        } catch (Exception e) {
            log.info("Error processing JWT authentication: {}", e.getMessage(), e);
            // Log the error and continue without authentication
            // The request will be rejected at the authorization filter if needed
        }

        filterChain.doFilter(request, response);
    }
}
