package com.adarsh.backend.feature.book.infrastructure.web.exception.constant;

public final class BookExceptionHandlerLogConstants {

    public static final String BOOK_ALREADY_EXISTS =
            "Book already exists with the given ISBN.";
    public static final String BOOK_NOT_FOUND =
            "Book not found.";

    private BookExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
