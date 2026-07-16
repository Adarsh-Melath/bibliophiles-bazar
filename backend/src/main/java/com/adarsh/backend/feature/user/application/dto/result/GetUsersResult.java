package com.adarsh.backend.feature.user.application.dto.result;

import com.adarsh.backend.feature.user.domain.model.User;

import java.time.LocalDateTime;

public record GetUsersResult(Long id, String name, String email, String role, String provider,
                             boolean enabled, boolean blocked, LocalDateTime createdAt) {

    public static GetUsersResult fromDomain(User user) {
        return new GetUsersResult(user.getId(), user.getName(), user.getEmail(), user.getRole().name(), user.getProvider().name(), user.isEnabled(), user.isBlocked(), user.getCreatedAt());
    }
}
