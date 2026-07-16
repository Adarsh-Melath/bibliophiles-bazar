package com.adarsh.backend.feature.category.infrastructure.web.exception.constant;

public final class CategoryExceptionHandlerLogConstants {

    public static final String CATEGORY_NOT_FOUND = "Category not found.";
    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists.";

    private CategoryExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}