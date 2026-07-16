package com.adarsh.backend.feature.auth.application.dto.result;

import com.adarsh.backend.feature.user.domain.model.User;

import java.time.LocalDateTime;

public record RefreshAccessTokenUserResult(Long id, String name, String email,
                                           String role, String profileImage,
                                           String phone,
                                           LocalDateTime createdAt) {

    public static RefreshAccessTokenUserResult fromDomain(User user) {
        return new RefreshAccessTokenUserResult(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getProfileImageUrl(),
                user.getPhone(), user.getCreatedAt());
    }
}
