package com.adarsh.backend.feature.cart.application.interactor.constant;

public final class CartInteractorLogConstants {

    // Add Cart Item
    public static final String ADD_CART_ITEM_REQUEST = "Adding cart item for user email={}, book slug={}";
    public static final String ADD_CART_ITEM_USER_FOUND = "User found with id={} for adding cart item";
    public static final String ADD_CART_ITEM_BOOK_FOUND = "Book found with id={} for adding cart item";
    public static final String ADD_CART_ITEM_CART_CREATED = "New cart created with id={} for user id={}";
    public static final String ADD_CART_ITEM_CART_FOUND = "Cart found with id={} for user id={}";
    public static final String ADD_CART_ITEM_SAVED = "Cart item saved successfully with id={}, cartId={}";

    // Delete Cart Item
    public static final String DELETE_CART_ITEM_REQUEST = "Deleting cart item with id={} for user email={}";
    public static final String DELETE_CART_ITEM_FOUND = "Cart item found with id={} for deletion";
    public static final String DELETE_CART_ITEM_DELETED = "Cart item deleted successfully with id={}";

    // Get Cart Items
    public static final String GET_CART_ITEMS_REQUEST = "Fetching cart items for user email={}";
    public static final String GET_CART_ITEMS_CART_FOUND = "Cart found with id={} for user id={}";
    public static final String GET_CART_ITEMS_FETCHED = "Cart items fetched successfully, count={}";

    private CartInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
