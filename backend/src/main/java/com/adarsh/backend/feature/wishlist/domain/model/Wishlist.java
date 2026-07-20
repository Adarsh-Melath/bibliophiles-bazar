package com.adarsh.backend.feature.wishlist.domain.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Wishlist {
    private final Long id;
    private final Long userId;
    private final List<WishlistItem> items;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Wishlist(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.items = builder.items;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = builder.updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<WishlistItem> getItems() {
        return items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private List<WishlistItem> items;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder items(List<WishlistItem> items) {
            this.items = items;
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

        public Wishlist build() {
            return new Wishlist(this);
        }
    }

    public void addItem(WishlistItem item) {
        this.items.add(item);
    }
}

