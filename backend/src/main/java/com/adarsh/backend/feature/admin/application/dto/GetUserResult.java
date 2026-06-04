package com.adarsh.backend.feature.admin.application.dto;

import java.time.LocalDateTime;

public class GetUserResult {

    private final Long id;
    private final String name;
    private final String email;
    private final String role;
    private final String provider;
    private final boolean enabled;
    private final boolean blocked;
    private final LocalDateTime createdAt;

    public GetUserResult(Long id, String name, String email, String role, String provider, boolean enabled, boolean blocked, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.enabled = enabled;
        this.blocked = blocked;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getProvider() {
        return provider;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
