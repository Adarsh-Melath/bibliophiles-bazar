package com.adarsh.backend.feature.wishlist.domain.exception.constant;

public final class WishlistExceptionMessageConstants {

    public static final String WISHLIST_ITEM_NOT_FOUND = "Wishlist item not found";
    public static final String WISHLIST_NOT_FOUND = "Wishlist not found";
    public static final String WISHLIST_ACCESS_DENIED = "Access to wishlist is denied";

    private WishlistExceptionMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
