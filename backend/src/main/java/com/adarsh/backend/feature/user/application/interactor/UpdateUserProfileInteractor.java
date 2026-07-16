package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.command.UpdateUserProfileCommand;
import com.adarsh.backend.feature.user.application.dto.result.UpdateUserProfileResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.UpdateUserProfileUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileInteractor implements UpdateUserProfileUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserProfileInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;

    @Override
    public UpdateUserProfileResult execute(String email, UpdateUserProfileCommand command) {
        logger.info(UserInteractorLogConstants.UPDATE_PROFILE_REQUEST, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        user.updateProfile(command.name(), command.phone(), command.profileImage());
        userCommandRepository.save(user);
        logger.info(UserInteractorLogConstants.UPDATE_PROFILE_SUCCESS, user.getId());

        return new UpdateUserProfileResult(user.getName(), user.getPhone(), user.getProfileImageUrl());
    }
}
