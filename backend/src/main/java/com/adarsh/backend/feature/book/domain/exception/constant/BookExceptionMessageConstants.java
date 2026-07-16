package com.adarsh.backend.feature.book.domain.exception.constant;

public final class BookExceptionMessageConstants {

    public static final String BOOK_ALREADY_EXISTS =
            "Book with this ISBN already exists";
    public static final String BOOK_NOT_FOUND =
            "Book not found";

    private BookExceptionMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
