package com.adarsh.backend.shared.infrastructure.security;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.shared.application.port.JwtUtilPort;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtilPort jwtUtilPort;
    private final UserCommandRepository userCommandRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

        String token=authHeader.substring(7);

        if(!jwtUtilPort.isTokenValid(token)){
            filterChain.doFilter(request,response);
        }

        String email=jwtUtilPort.extractClaims(token).getSubject();
        String role=jwtUtilPort.extractClaims(token).get("role",String.class);

        userCommandRepository.findByEmail(email).ifPresent(user->{
            if(user.isEnabled() && !user.isBlocked()){
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
                    email,null,List.of(new SimpleGrantedAuthority("ROLE_"+role))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        });

        filterChain.doFilter(request,response);
    }
}
