package com.adarsh.backend.shared.application.dto;

public class UploadUserProfileImageResult {
    private final String url;

    public UploadUserProfileImageResult(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
