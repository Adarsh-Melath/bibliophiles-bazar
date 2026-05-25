package com.adarsh.backend.shared.domain;

import java.time.LocalDateTime;

public final class OtpToken {

    private final Long id;

    private final String email;

    private final String code;

    private final LocalDateTime expiresAt;

    private String resetToken;

    // All-args constructor
    private OtpToken(
            Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.code = builder.code;
        this.expiresAt = builder.expiresAt;
    }

    public void generateResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public String getResetToken() {
        return resetToken;
    }

    // Business method

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public static Builder builder(){
        return new Builder();
    }
    @SuppressWarnings("unused")
    public static class Builder {
        private Long id;

        private String email;

        private String code;

        private LocalDateTime expiresAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public OtpToken build() {
            return new OtpToken(this);
        }
    }
}