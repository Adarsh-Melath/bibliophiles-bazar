package com.adarsh.backend.feature.cart.presentation.constant;

public final class CartControllerLogConstants {

    // Add Cart Item
    public static final String ADD_CART_ITEM_REQUEST = "POST /api/v1/cart - Adding cart item for email={}";
    public static final String ADD_CART_ITEM_SUCCESS = "POST /api/v1/cart - Cart item added successfully, id={}";

    // Delete Cart Item
    public static final String DELETE_CART_ITEM_REQUEST = "DELETE /api/v1/cart/{} - Deleting cart item for email={}";
    public static final String DELETE_CART_ITEM_SUCCESS = "DELETE /api/v1/cart/{} - Cart item deleted successfully";

    // Get Cart Items
    public static final String GET_CART_ITEMS_REQUEST = "GET /api/v1/cart - Fetching cart items for email={}";
    public static final String GET_CART_ITEMS_SUCCESS = "GET /api/v1/cart - Cart items fetched successfully, count={}";

    private CartControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
