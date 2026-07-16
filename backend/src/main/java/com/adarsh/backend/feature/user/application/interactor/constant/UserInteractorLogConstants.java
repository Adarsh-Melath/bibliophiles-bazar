package com.adarsh.backend.feature.user.application.interactor.constant;

public class UserInteractorLogConstants {

    private UserInteractorLogConstants() {
    }

    // GetUserProfileInteractor
    public static final String GET_PROFILE_REQUEST = "Fetching user profile for email: {}";
    public static final String GET_PROFILE_FETCHED = "User profile fetched successfully. User ID: {}";

    // GetPublishedBooksInteractor
    public static final String GET_PUBLISHED_BOOKS_REQUEST = "Fetching published books for user ID: {}";
    public static final String GET_PUBLISHED_BOOKS_FETCHED = "Published books fetched successfully for user ID: {}";

    // GetAddressesInteractor
    public static final String GET_ADDRESSES_REQUEST = "Fetching addresses for user ID: {}";
    public static final String GET_ADDRESSES_FETCHED = "Addresses fetched successfully for user ID: {}";
    public static final String GET_ADDRESSES_EMPTY = "No addresses found for user ID: {}";

    // AddAddressInteractor
    public static final String ADD_ADDRESS_REQUEST = "Adding new address for user ID: {}";
    public static final String ADD_ADDRESS_SUCCESS = "Address added successfully. Address ID: {}";

    // UpdateAddressInteractor
    public static final String UPDATE_ADDRESS_REQUEST = "Updating address ID: {} for user ID: {}";
    public static final String UPDATE_ADDRESS_SUCCESS = "Address updated successfully. Address ID: {}";

    // DeleteAddressInteractor
    public static final String DELETE_ADDRESS_REQUEST = "Deleting address ID: {}";
    public static final String DELETE_ADDRESS_SUCCESS = "Address deleted successfully. Address ID: {}";

    // UpdateUserProfileInteractor
    public static final String UPDATE_PROFILE_REQUEST = "Updating user profile for email: {}";
    public static final String UPDATE_PROFILE_SUCCESS = "User profile updated successfully. User ID: {}";

    // ChangePasswordInteractor
    public static final String CHANGE_PASSWORD_REQUEST = "Processing password change request for email: {}";
    public static final String CHANGE_PASSWORD_SUCCESS = "Password changed successfully for email: {}";

    // ChangeEmailInteractor
    public static final String CHANGE_EMAIL_REQUEST = "Processing email change request for current email: {}";
    public static final String CHANGE_EMAIL_SUCCESS = "Email changed successfully. New email: {}";

    // GetUsersInteractor
    public static final String GET_USERS_REQUEST = "Fetching users with pagination. Page: {}, Size: {}";
    public static final String GET_USERS_FETCHED = "Users fetched successfully. Total count: {}";

    // ToggleBlockInteractor
    public static final String TOGGLE_BLOCK_REQUEST = "Toggling block status for user ID: {}";
    public static final String TOGGLE_BLOCK_SUCCESS = "Block status toggled successfully for user ID: {}. New status: {}";

    // DeleteUserInteractor
    public static final String DELETE_USER_REQUEST = "Processing user deletion request for user ID: {}";
    public static final String DELETE_USER_SUCCESS = "User deleted successfully. User ID: {}";
}
