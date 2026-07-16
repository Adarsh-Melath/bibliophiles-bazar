package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.result.GetUserProfileResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.usecase.GetUserProfileUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserProfileInteractor implements GetUserProfileUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetUserProfileInteractor.class);
    private final UserCommandRepository userCommandRepository;

    @Override
    public GetUserProfileResult execute(String email) {
        logger.info(UserInteractorLogConstants.GET_PROFILE_REQUEST, email);

        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        logger.info(UserInteractorLogConstants.GET_PROFILE_FETCHED, user.getId());

        return new GetUserProfileResult(user.getName(), user.getEmail(), user.getRole().name(), user.getProfileImageUrl(), user.getPhone(), user.getCreatedAt());
    }
}
