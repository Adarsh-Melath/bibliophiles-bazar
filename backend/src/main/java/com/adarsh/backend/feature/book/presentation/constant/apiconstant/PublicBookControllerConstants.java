package com.adarsh.backend.feature.book.presentation.constant.apiconstant;

public final class PublicBookControllerConstants {

    public static final String BASE_PATH = "/api/v1/books";

    public static final String BOOK_BY_SLUG_PATH = "/{slug}";

    public static final String DEFAULT_SORT_OPTION = "NEWEST";
    public static final String DEFAULT_MIN_PRICE = "0";
    public static final String DEFAULT_MAX_PRICE = "10000";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    private PublicBookControllerConstants() {
    }
}