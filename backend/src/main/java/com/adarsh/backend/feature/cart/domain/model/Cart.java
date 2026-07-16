package com.adarsh.backend.feature.cart.domain.model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class Cart {

    private final Long id;

    private final Long userId;

    private final List<CartItem> items;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Cart(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.items = builder.items;
        this.createdAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private List<CartItem> items;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder items(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }

    public Long getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
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

    public void addItem(CartItem item) {
        items.add(item);
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }
}
