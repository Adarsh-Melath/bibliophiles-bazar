package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.command.ChangePasswordCommand;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.ChangePasswordUseCase;
import com.adarsh.backend.feature.user.domain.exception.PasswordIncorrectException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordInteractor implements ChangePasswordUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordInteractor.class);
    private final PasswordEncoder passwordEncoder;
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;

    @Override
    public void execute(String email, ChangePasswordCommand command) {
        logger.info(UserInteractorLogConstants.CHANGE_PASSWORD_REQUEST, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        if (!passwordEncoder.matches(command.currentPassword(), user.getPassword())) {
            throw new PasswordIncorrectException(AuthExceptionMessageConstants.INVALID_CURRENT_PASSWORD);
        }

        user.changePassword(passwordEncoder.encode(command.newPassword()));
        userCommandRepository.save(user);
        logger.info(UserInteractorLogConstants.CHANGE_PASSWORD_SUCCESS, email);
    }
}
