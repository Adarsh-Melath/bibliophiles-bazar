package com.adarsh.backend.feature.user.application.dto.result;

import java.time.LocalDateTime;

public record GetUserProfileResult(String name, String email, String role, String profileImage,
                                   String phone, LocalDateTime createdAt) {
}
