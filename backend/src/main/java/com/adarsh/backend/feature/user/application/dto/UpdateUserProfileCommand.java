package com.adarsh.backend.feature.user.application.dto;

public class UpdateUserProfileCommand {

    private final  String name;

    private final String phone;

    private final String profileImage;

    public UpdateUserProfileCommand(String name, String phone, String profileImage) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Name must be between 2 and 50 characters.");
        }

        if (phone != null && !phone.trim().isEmpty()) {
            if (!phone.matches("^\\+?[0-9]{7,15}$")) {
                throw new IllegalArgumentException("Invalid phone number format.");
            }
        }
        this.name = name;
        this.phone = phone;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
