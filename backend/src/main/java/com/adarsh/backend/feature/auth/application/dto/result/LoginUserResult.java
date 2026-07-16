package com.adarsh.backend.feature.auth.application.dto.result;

import com.adarsh.backend.feature.user.domain.model.User;

public record LoginUserResult(Long id, String name, String email, String role,
                              String profileImage, String phone) {

    public static LoginUserResult fromDomain(User user) {
        return new LoginUserResult(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getProfileImageUrl(),
                user.getPhone());
    }
}
