package com.adarsh.backend.feature.user.domain.model;

import java.time.LocalDateTime;

public class User {

    // fields
    private final Long id;

    private final LocalDateTime createdAt;

    private String email;
    private String name;

    private String password;

    private String phone;

    private String profileImageUrl;

    private final Role role;

    private final AuthProvider provider;

    private boolean enabled;

    private boolean blocked;

    private boolean deleted;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    // 1. Private constructor so it can ONLY be created via the Builder
    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phone = builder.phone;
        this.profileImageUrl = builder.profileImageUrl;
        this.role = builder.role;
        this.provider = builder.provider;
        this.enabled = builder.enabled;
        this.blocked = builder.blocked;
        this.deleted = builder.deleted;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.deletedAt = builder.deletedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public Role getRole() {
        return role;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void enableAccount() {
        this.enabled = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void changePassword(String password) {
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateProfile(String name, String phone, String profileImageUrl) {
        this.name = name;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public void uploadProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public void block() {
        this.blocked = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void unblock() {
        this.blocked = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
        this.updatedAt = LocalDateTime.now();
    }

    // 3. The Static Builder Class
    @SuppressWarnings("unused")
    public static class Builder {

        private Long id;

        private String name;

        private String email;

        private String password;

        private String phone;

        private String profileImageUrl;

        private Role role;

        private AuthProvider provider;

        private boolean enabled;

        private boolean blocked;

        private boolean deleted;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        private LocalDateTime deletedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder profileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder provider(AuthProvider provider) {
            this.provider = provider;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder blocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public Builder deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        // The build method that finally constructs the User
        public User build() {
            return new User(this);
        }
    }
}
