package com.adarsh.backend.feature.category.application.interactor.constant;

public final class CategoryInteractorLogConstants {

    // Add Category
    public static final String ADD_CATEGORY_REQUEST = "Creating category with type={}";
    public static final String ADD_CATEGORY_SAVED = "Category saved with id={}";
    // Get Categories
    public static final String GET_CATEGORIES_REQUEST = "Fetching categories with keyword={}, page={}, size={}";
    public static final String GET_CATEGORIES_FETCHED = "Categories fetched.";
    // Delete Category
    public static final String DELETE_CATEGORY_REQUEST = "Deleting category with slug={}";
    public static final String DELETE_CATEGORY_SAVED = "Category soft deleted and saved with id={}";
    // Edit Category
    public static final String EDIT_CATEGORY_REQUEST = "Updating category with slug={}";
    public static final String EDIT_CATEGORY_SAVED = "Category updated and saved with id={}";

    private CategoryInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}