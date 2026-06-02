package com.adarsh.backend.feature.user.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.dto.GetUserProfileResult;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.usecase.GetUserProfileUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserProfileInteractor implements GetUserProfileUseCase {
    private final UserCommandRepository userCommandRepository;

    @Override
    public GetUserProfileResult execute(String email) {
        User user = userCommandRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        return new GetUserProfileResult(user.getName(), user.getEmail(), user.getRole().name(),
                user.getProfileImageUrl(), user.getPhone());
    }

}
