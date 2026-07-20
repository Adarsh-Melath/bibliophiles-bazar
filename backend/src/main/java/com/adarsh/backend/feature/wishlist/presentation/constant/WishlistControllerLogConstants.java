package com.adarsh.backend.feature.wishlist.presentation.constant;

public final class WishlistControllerLogConstants {

    // Add Wishlist Item
    public static final String ADD_WISHLIST_ITEM_REQUEST = "POST /api/v1/wishlist - Adding wishlist item for email={}";
    public static final String ADD_WISHLIST_ITEM_SUCCESS = "POST /api/v1/wishlist - Wishlist item added successfully, bookId={}";

    // Delete Wishlist Item
    public static final String DELETE_WISHLIST_ITEM_REQUEST = "DELETE /api/v1/wishlist/{} - Deleting wishlist item for email={}";
    public static final String DELETE_WISHLIST_ITEM_SUCCESS = "DELETE /api/v1/wishlist/{} - Wishlist item deleted successfully";

    // Get Wishlist Items
    public static final String GET_WISHLIST_ITEMS_REQUEST = "GET /api/v1/wishlist - Fetching wishlist items for email={}";
    public static final String GET_WISHLIST_ITEMS_SUCCESS = "GET /api/v1/wishlist - Wishlist items fetched successfully, count={}";

    private WishlistControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
