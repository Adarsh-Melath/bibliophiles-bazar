package com.adarsh.backend.feature.auth.application.dto;

import com.adarsh.backend.feature.user.domain.model.User;

public class LoginUserResult {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String profileImage;
    private String phone;

    public LoginUserResult(Long id, String name, String email, String role, String profileImage, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.profileImage = profileImage;
        this.phone = phone;
    }

    public Long getId() {
        return id;
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

    public String getProfileImage() {
        return profileImage;
    }

    public String getPhone() {
        return phone;
    }

    public LoginUserResult fromDomain(User user) {
        return new LoginUserResult(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getProfileImageUrl(),
                user.getPhone());
    }
}
