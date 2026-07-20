package com.adarsh.backend.feature.wishlist.domain.model;

import java.time.LocalDateTime;

public class WishlistItem {
    private final Long id;
    private final Long wishlistId;
    private final Long bookId;
    private final LocalDateTime createdAt;

    public WishlistItem(Builder builder) {
        this.id = builder.id;
        this.wishlistId = builder.wishlistId;
        this.bookId = builder.bookId;
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public Long getBookId() {
        return bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long wishlistId;
        private Long bookId;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder wishlistId(Long wishlistId) {
            this.wishlistId = wishlistId;
            return this;
        }

        public Builder bookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public WishlistItem build() {
            return new WishlistItem(this);
        }
    }
}