package com.adarsh.backend.feature.user.application.dto;

public class UpdateUserProfileResult {

    private final String name;
    private final String phone;
    private final String profileImageUrl;

    public UpdateUserProfileResult(String name, String phone, String profileImageUrl) {
        this.name = name;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
