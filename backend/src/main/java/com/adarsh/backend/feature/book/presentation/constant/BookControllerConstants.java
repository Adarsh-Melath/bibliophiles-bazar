package com.adarsh.backend.feature.book.presentation.constant;

public final class BookControllerConstants {

    // Base Path
    public static final String BASE_PATH = "/api/v1/books";

    // Endpoints
    public static final String UPDATE_PATH = "/update/{slug}";
    public static final String DELETE_PATH = "/delete/{slug}";

    private BookControllerConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
