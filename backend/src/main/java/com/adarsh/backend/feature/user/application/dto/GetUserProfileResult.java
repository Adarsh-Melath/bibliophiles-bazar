package com.adarsh.backend.feature.user.application.dto;

public class GetProfileResult {
    private String name;
    private String email;
    private String role;
    private String profileImageUrl;
    private String phone;

    public GetProfileResult(String name, String email, String role, String profileImageUrl, String phone) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
        this.phone = phone;
    }
}
