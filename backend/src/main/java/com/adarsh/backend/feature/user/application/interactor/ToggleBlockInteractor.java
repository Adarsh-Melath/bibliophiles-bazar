package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.ToggleBlockUseCase;
import com.adarsh.backend.feature.user.domain.exception.CannotBlockAdminUserException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToggleBlockInteractor implements ToggleBlockUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ToggleBlockInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;

    @Override
    public void execute(Long userId) {
        logger.info(UserInteractorLogConstants.TOGGLE_BLOCK_REQUEST, userId);
        User user = userQueryRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        if (user.getRole().name().equals("ADMIN")) {
            throw new CannotBlockAdminUserException(UserExceptionMessageConstants.CANNOT_BLOCK_ADMIN_USER_EXCEPTION);
        }

        if (user.isBlocked()) {
            user.unblock();
        } else {
            user.block();
        }

        userCommandRepository.save(user);
        logger.info(UserInteractorLogConstants.TOGGLE_BLOCK_SUCCESS, userId, user.isBlocked());
    }
}
