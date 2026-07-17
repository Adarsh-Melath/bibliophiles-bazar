package com.adarsh.backend.feature.book.presentation.constant.logconstant;

public final class BookControllerLogConstants {

    public static final String ADD_BOOK_REQUEST =
            "Received request to add book with title: {}";
    public static final String ADD_BOOK_SUCCESS =
            "Book added successfully with ID: {}";
    public static final String UPDATE_BOOK_REQUEST =
            "Received request to update book with slug: {}";
    public static final String UPDATE_BOOK_SUCCESS =
            "Book updated successfully with slug: {}";
    public static final String DELETE_BOOK_REQUEST =
            "Received request to delete book with slug: {}";
    public static final String DELETE_BOOK_SUCCESS =
            "Book deleted successfully with slug: {}";
    public static final String GET_BOOKS_REQUEST =
            "Received request to fetch public books with keyword: {}, page: {}, size: {}";
    public static final String GET_BOOKS_SUCCESS =
            "Public books fetched successfully";
    public static final String GET_PUBLISHER_BOOKS_REQUEST =
            "Received request to fetch publisher books with keyword: {}, page: {}, size: {}";
    public static final String GET_PUBLISHER_BOOKS_SUCCESS =
            "Publisher books fetched successfully";

    private BookControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
