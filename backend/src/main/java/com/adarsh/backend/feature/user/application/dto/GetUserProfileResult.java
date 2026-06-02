package com.adarsh.backend.feature.user.application.dto;

public class GetUserProfileResult {
    private String name;
    private String email;
    private String role;
    private String profileImageUrl;
    private String phone;

    public GetUserProfileResult(String name, String email, String role, String profileImageUrl, String phone) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getPhone() {
        return phone;
    }
}
