package com.adarsh.backend.feature.auth.domain;

import java.time.LocalDateTime;

public class RefreshToken {
    private final Long id;
    private final String token;
    private final String email;
    private final LocalDateTime expiresAt;

    public RefreshToken(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.email = builder.email;
        this.expiresAt = builder.expiresAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public static class Builder {
        private Long id;
        private String token;
        private String email;
        private LocalDateTime expiresAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public RefreshToken build() {
            return new RefreshToken(this);
        }
    }
}
