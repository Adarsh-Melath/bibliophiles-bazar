package com.adarsh.backend.feature.order.application.interactor.constant;

public final class OrderInteractorLogConstants {

    // Place Order
    public static final String PLACE_ORDER_REQUEST = "Placing order for user email={}";
    public static final String PLACE_ORDER_USER_FOUND = "User found with id={} for placing order";
    public static final String PLACE_ORDER_ADDRESS_FOUND = "Address found with id={} for placing order";
    public static final String PLACE_ORDER_CART_FOUND = "Cart found with id={} for user id={}";
    public static final String PLACE_ORDER_BOOK_FOUND = "Book found with id={} for stock check";
    public static final String PLACE_ORDER_STOCK_CHECK = "Stock check successful for book id={}, quantity={}";
    public static final String PLACE_ORDER_STOCK_DEDUCTED = "Stock deducted for book id={}, new stock={}";
    public static final String PLACE_ORDER_SAVED = "Order saved successfully with id={}, orderNumber={}";
    public static final String PLACE_ORDER_CART_CLEARED = "Cart cleared successfully for cart id={}";

    // Cancel Order
    public static final String CANCEL_ORDER_REQUEST = "Cancelling order with id={} for user email={}";
    public static final String CANCEL_ORDER_USER_FOUND = "User found with id={} for cancelling order";
    public static final String CANCEL_ORDER_FOUND = "Order found with id={} for cancelling";
    public static final String CANCEL_ORDER_SUCCESS = "Order cancelled successfully with id={}";
    public static final String CANCEL_ORDER_STOCK_RESTORED = "Restored stock for book id={}, added quantity={}";

    // Cancel Specific Product
    public static final String CANCEL_PRODUCT_REQUEST = "Cancelling specific product item id={} in order id={} for user email={}";
    public static final String CANCEL_PRODUCT_USER_FOUND = "User found with id={} for cancelling product";
    public static final String CANCEL_PRODUCT_ORDER_FOUND = "Order found with id={} for product cancellation";
    public static final String CANCEL_PRODUCT_SUCCESS = "Product item id={} cancelled successfully in order id={}";
    public static final String CANCEL_PRODUCT_STOCK_RESTORED = "Restored stock for book id={}, added quantity={}";

    // Return Order
    public static final String RETURN_ORDER_REQUEST = "Request to return order with id={} for user email={}";
    public static final String RETURN_ORDER_USER_FOUND = "User found with id={} for return order";
    public static final String RETURN_ORDER_FOUND = "Order found with id={} for return";
    public static final String RETURN_ORDER_SUCCESS = "Order status updated to RETURN_REQUESTED for order id={}";

    // Return Specific Item
    public static final String RETURN_ITEM_REQUEST = "Request to return order item id={} in order id={} for user email={}";
    public static final String RETURN_ITEM_USER_FOUND = "User found with id={} for return item";
    public static final String RETURN_ITEM_SUCCESS = "Order item id={} updated to RETURN_REQUESTED in order id={}";

    // Generate Invoice
    public static final String GENERATE_INVOICE_REQUEST = "Request to generate invoice for order id={}, user email={}";
    public static final String GENERATE_INVOICE_USER_FOUND = "User found with id={} for generating invoice";
    public static final String GENERATE_INVOICE_FOUND = "Order found with id={} for generating invoice";
    public static final String GENERATE_INVOICE_SUCCESS = "Invoice PDF generated successfully for order id={}";

    // Get Order Details
    public static final String GET_ORDER_DETAILS_REQUEST = "Requesting details for order id={} for user email={}";
    public static final String GET_ORDER_DETAILS_USER_FOUND = "User found with id={} for getting order details";
    public static final String GET_ORDER_DETAILS_FOUND = "Order found with id={} for details";
    public static final String GET_ORDER_DETAILS_SUCCESS = "Order details retrieved successfully for order id={}";

    // Search Customer Orders
    public static final String SEARCH_ORDERS_REQUEST = "Searching orders for user email={}, page={}, size={}";
    public static final String SEARCH_ORDERS_USER_FOUND = "User found with id={} for searching orders";
    public static final String SEARCH_ORDERS_SUCCESS = "Retrieved {} orders successfully for user id={}";

    private OrderInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
