package com.adarsh.backend.feature.user.application.dto;

public class UploadUserProfileImageResult {
    private final String url;

    public UploadUserProfileImageResult(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
