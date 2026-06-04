package com.adarsh.backend.feature.user.application.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadUserProfileImageCommand {
    private MultipartFile file;

    public UploadUserProfileImageCommand(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}
