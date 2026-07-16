package com.adarsh.backend.feature.book.application.interactor.constant;

public final class BookInteractorLogConstants {

    // Add Book
    public static final String ADD_BOOK_REQUEST =
            "Adding book for publisher email={}, isbn={}";
    public static final String ADD_BOOK_ISBN_CHECK =
            "Checking if book with isbn={} already exists";
    public static final String ADD_BOOK_SLUG_GENERATED =
            "Generated slug={} for book title={}";
    public static final String ADD_BOOK_SAVED =
            "Book saved successfully with id={}, isbn={}";

    // Delete Book
    public static final String DELETE_BOOK_REQUEST =
            "Deleting book with slug={}";
    public static final String DELETE_BOOK_FOUND =
            "Book found with slug={} for deletion";
    public static final String DELETE_BOOK_DELETED =
            "Book soft deleted successfully with slug={}";

    // Update Book
    public static final String UPDATE_BOOK_REQUEST =
            "Updating book with slug={}";
    public static final String UPDATE_BOOK_FOUND =
            "Book found with slug={} for update";
    public static final String UPDATE_BOOK_SAVED =
            "Book updated successfully with slug={}";

    // Get Books
    public static final String GET_BOOKS_REQUEST =
            "Fetching books with keyword={}, page={}, size={}";
    public static final String GET_BOOKS_FETCHED =
            "Books fetched successfully, total elements={}";

    // Get Published Books
    public static final String GET_PUBLISHED_BOOKS_REQUEST =
            "Fetching published books with keyword={}, sortOption={}, category={}, minPrice={}, maxPrice={}, page={}, size={}";
    public static final String GET_PUBLISHED_BOOKS_FETCHED =
            "Published books fetched successfully, total elements={}";

    private BookInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
