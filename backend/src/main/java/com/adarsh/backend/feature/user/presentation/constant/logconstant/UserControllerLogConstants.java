package com.adarsh.backend.feature.user.presentation.constant.logconstant;

public class UserControllerLogConstants {

    private UserControllerLogConstants() {
    }

    // UserAccountController
    public static final String CHANGE_PASSWORD_REQUEST = "User requested to change password for email: {}";
    public static final String CHANGE_PASSWORD_SUCCESS = "Password changed successfully for email: {}";
    public static final String CHANGE_EMAIL_REQUEST = "User requested to change email from: {}";
    public static final String CHANGE_EMAIL_SUCCESS = "Email changed successfully for user: {}";

    // UserProfileController
    public static final String GET_PROFILE_REQUEST = "User requested profile for email: {}";
    public static final String UPDATE_PROFILE_REQUEST = "User requested to update profile for email: {}";
    public static final String UPDATE_PROFILE_SUCCESS = "Profile updated successfully for email: {}";

    // UserAddressController
    public static final String ADD_ADDRESS_REQUEST = "User requested to add address for email: {}";
    public static final String ADD_ADDRESS_SUCCESS = "Address added successfully for email: {}";
    public static final String GET_ADDRESSES_REQUEST = "User requested to fetch addresses for email: {}";
    public static final String UPDATE_ADDRESS_REQUEST = "User requested to update address with ID: {} for email: {}";
    public static final String UPDATE_ADDRESS_SUCCESS = "Address updated successfully for address ID: {}";
    public static final String DELETE_ADDRESS_REQUEST = "User requested to delete address with ID: {}";
    public static final String DELETE_ADDRESS_SUCCESS = "Address deleted successfully for address ID: {}";

    // UserBookController
    public static final String GET_PUBLISHED_BOOKS_REQUEST = "User requested to fetch published books for user ID: {}";

    // AdminUserController
    public static final String GET_USERS_REQUEST = "Admin requested to fetch users with pagination. Page: {}, Size: {}";
    public static final String TOGGLE_BLOCK_REQUEST = "Admin requested to toggle block status for user ID: {}";
    public static final String TOGGLE_BLOCK_SUCCESS = "Block status toggled successfully for user ID: {}";
    public static final String DELETE_USER_REQUEST = "Admin requested to delete user ID: {}";
    public static final String DELETE_USER_SUCCESS = "User deleted successfully. User ID: {}";
}
