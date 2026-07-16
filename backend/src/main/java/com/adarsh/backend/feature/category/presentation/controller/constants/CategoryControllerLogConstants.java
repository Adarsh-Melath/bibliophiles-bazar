package com.adarsh.backend.feature.category.presentation.controller.constants;

public final class CategoryControllerLogConstants {

    // Add Category
    public static final String ADD_CATEGORY_REQUEST = "Received add category request for type={}";
    public static final String ADD_CATEGORY_SUCCESS = "Category created successfully with slug={}";
    // Get Categories
    public static final String GET_CATEGORIES_REQUEST = "Received get categories request with keyword={}, page={}, size={}";
    public static final String GET_CATEGORIES_SUCCESS = "Categories retrieved successfully.";
    // Delete Category
    public static final String DELETE_CATEGORY_REQUEST = "Received delete category request for slug={}";
    public static final String DELETE_CATEGORY_SUCCESS = "Category deleted successfully for slug={}";
    // Update Category
    public static final String UPDATE_CATEGORY_REQUEST = "Received update category request for slug={}";
    public static final String UPDATE_CATEGORY_SUCCESS = "Category updated successfully for slug={}";

    private CategoryControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}