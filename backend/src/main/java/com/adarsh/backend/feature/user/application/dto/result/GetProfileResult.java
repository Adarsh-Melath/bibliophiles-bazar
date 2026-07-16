package com.adarsh.backend.feature.user.application.dto.result;

public record GetProfileResult(String name, String email, String role, String profileImageUrl,
                               String phone) {
}
