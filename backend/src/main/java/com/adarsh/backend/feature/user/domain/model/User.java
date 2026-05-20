package com.adarsh.backend.feature.user.domain.model;

import java.security.AuthProvider;
import java.time.LocalDateTime;

public class User {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private String profileImage;

    private Role role = Role.USER;

    private AuthProvider provider = AuthProvider.LOCAL;

    private boolean enabled;

    private boolean blocked;

    private boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
