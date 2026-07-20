package com.adarsh.backend.feature.wishlist.infrastructure.web.exception.constant;

public final class WishlistExceptionHandlerLogConstants {

    public static final String WISHLIST_ITEM_NOT_FOUND = "Wishlist item not found exception occurred";
    public static final String WISHLIST_NOT_FOUND = "Wishlist not found exception occurred";
    public static final String WISHLIST_ACCESS_DENIED = "Wishlist access denied exception occurred";

    private WishlistExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
