package com.adarsh.backend.feature.user.application.usecase;


import com.adarsh.backend.feature.user.application.dto.UploadUserProfileImageCommand;
import com.adarsh.backend.feature.user.application.dto.UploadUserProfileImageResult;

public interface UploadUserProfileImageUseCase {
    UploadUserProfileImageResult execute(String email, UploadUserProfileImageCommand command);
}
