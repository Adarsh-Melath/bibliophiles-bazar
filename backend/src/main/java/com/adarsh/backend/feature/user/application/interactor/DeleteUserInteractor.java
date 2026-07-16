package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.DeleteUserUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserInteractor implements DeleteUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;

    @Override
    public void execute(Long id) {
        logger.info(UserInteractorLogConstants.DELETE_USER_REQUEST, id);
        User user = userQueryRepository.findById(id).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        user.softDelete();
        userCommandRepository.save(user);
        logger.info(UserInteractorLogConstants.DELETE_USER_SUCCESS, id);
    }
}
