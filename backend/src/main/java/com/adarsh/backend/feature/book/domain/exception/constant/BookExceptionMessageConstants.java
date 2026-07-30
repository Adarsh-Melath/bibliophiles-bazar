package com.adarsh.backend.feature.book.domain.exception.constant;

public final class BookExceptionMessageConstants {

    public static final String BOOK_ALREADY_EXISTS =
            "Book with this ISBN already exists";
    public static final String BOOK_NOT_FOUND =
            "Book not found";
    public static final String INVALID_DEDUCT_QUANTITY =
            "Quantity to deduct must be positive";
    public static final String INSUFFICIENT_STOCK =
            "Insufficient stock to deduct";

    private BookExceptionMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
