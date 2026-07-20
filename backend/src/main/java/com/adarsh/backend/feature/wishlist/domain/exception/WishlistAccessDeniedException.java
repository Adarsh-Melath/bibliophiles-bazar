package com.adarsh.backend.feature.wishlist.domain.exception;

public class WishlistAccessDeniedException extends RuntimeException {
    public WishlistAccessDeniedException(String message) {
        super(message);
    }
}
