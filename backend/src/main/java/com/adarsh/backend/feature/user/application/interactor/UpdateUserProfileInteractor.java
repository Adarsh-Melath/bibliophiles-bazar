package com.adarsh.backend.feature.user.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.dto.UpdateUserProfileCommand;
import com.adarsh.backend.feature.user.application.dto.UpdateUserProfileResult;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.application.usecase.UpdateUserProfileUseCase;

import com.adarsh.backend.feature.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileInteractor implements UpdateUserProfileUseCase {

    private final UserCommandRepository userCommandRepository;

    @Override
    public UpdateUserProfileResult execute(String email, UpdateUserProfileCommand command) {
        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found "));

        user.updateProfile(command.getName(), command.getPhone(), command.getProfileImage());
        userCommandRepository.save(user);
        return new UpdateUserProfileResult(user.getName(), user.getPhone(), user.getProfileImageUrl());
    }

}
