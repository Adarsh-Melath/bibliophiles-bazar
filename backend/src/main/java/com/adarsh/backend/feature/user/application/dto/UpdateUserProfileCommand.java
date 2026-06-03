package com.adarsh.backend.feature.user.application.dto;

public class UpdateUserProfileCommand {

    private final  String name;

    private final String phone;

    private final String profileImage;

    public UpdateUserProfileCommand(String name, String phone, String profileImage) {
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
