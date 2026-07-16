package com.adarsh.backend.feature.cart.domain.model;

import com.adarsh.backend.feature.cart.domain.exception.constant.CartExceptionMessageConstants;

public class CartItem {
    private final Long id;

    private final Long cartId;

    private final Long bookId;

    private int quantity;

    private Double unitPrice;

    public CartItem(Builder builder) {
        this.id = builder.id;
        this.cartId = builder.cartId;
        this.bookId = builder.bookId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
    }

    public static class Builder {
        private Long id;
        private Long cartId;
        private Long bookId;
        private int quantity;
        private Double unitPrice;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder cartId(Long cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder bookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void updateQuantity(int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException(CartExceptionMessageConstants.INVALID_QUANTITY);
        }
        if (newQuantity > CartExceptionMessageConstants.MAX_QUANTITY_LIMIT) {
            throw new IllegalArgumentException(String.format(
                    CartExceptionMessageConstants.MAX_QUANTITY_EXCEEDED, CartExceptionMessageConstants.MAX_QUANTITY_LIMIT));
        }
        this.quantity = newQuantity;
    }
}
