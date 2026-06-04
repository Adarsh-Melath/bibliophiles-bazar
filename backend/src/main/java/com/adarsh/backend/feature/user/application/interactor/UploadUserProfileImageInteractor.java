package com.adarsh.backend.feature.user.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.dto.UploadUserProfileImageCommand;
import com.adarsh.backend.feature.user.application.dto.UploadUserProfileImageResult;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.usecase.UploadUserProfileImageUseCase;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.port.ObjectStoragePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadUserProfileImageInteractor implements UploadUserProfileImageUseCase {
    private final UserCommandRepository userCommandRepository;
    private final  ObjectStoragePort objectStoragePort;

    @Override
    public UploadUserProfileImageResult execute(String email, UploadUserProfileImageCommand command) {
        User user =userCommandRepository.findByEmail(email).orElseThrow();

        if(user.getProfileImageUrl() != null){
            String key = objectStoragePort.extractKeyFromUrl(user.getProfileImageUrl());
            objectStoragePort.deleteFile(key);
        }
        
        String key = "profiles/" + user.getId() + "-" + System.currentTimeMillis();
        String url = objectStoragePort.uploadFile(command.getFile(), key);

        user.uploadProfileImage(url);
        userCommandRepository.save(user);
        return new UploadUserProfileImageResult(url);
    }
} 
