package com.adarsh.backend.feature.wishlist.application.interactor.constant;

public final class WishlistInteractorLogConstants {

    // Add Wishlist Item
    public static final String ADD_WISHLIST_ITEM_REQUEST = "Adding wishlist item for user email={}, book slug={}";
    public static final String ADD_WISHLIST_ITEM_USER_FOUND = "User found with id={} for adding wishlist item";
    public static final String ADD_WISHLIST_ITEM_BOOK_FOUND = "Book found with id={} for adding wishlist item";
    public static final String ADD_WISHLIST_ITEM_WISHLIST_CREATED = "New wishlist created with id={} for user id={}";
    public static final String ADD_WISHLIST_ITEM_WISHLIST_FOUND = "Wishlist found with id={} for user id={}";
    public static final String ADD_WISHLIST_ITEM_SAVED = "Wishlist item saved successfully for book id={}, wishlistId={}";

    // Delete Wishlist Item
    public static final String DELETE_WISHLIST_ITEM_REQUEST = "Deleting wishlist item with id={} for user email={}";
    public static final String DELETE_WISHLIST_ITEM_FOUND = "Wishlist item found with id={} for deletion";
    public static final String DELETE_WISHLIST_ITEM_DELETED = "Wishlist item deleted successfully with id={}";

    // Get Wishlist Items
    public static final String GET_WISHLIST_ITEMS_REQUEST = "Fetching wishlist items for user email={}";
    public static final String GET_WISHLIST_ITEMS_WISHLIST_FOUND = "Wishlist found with id={} for user id={}";
    public static final String GET_WISHLIST_ITEMS_FETCHED = "Wishlist items fetched successfully, count={}";

    private WishlistInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
