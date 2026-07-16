package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.dto.command.ResetPasswordCommand;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.application.port.PasswordResetTokenPort;
import com.adarsh.backend.feature.auth.application.usecase.ResetPasswordUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordInteractor implements ResetPasswordUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final PasswordResetTokenPort passwordResetTokenPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(ResetPasswordCommand command) {
        logger.info(AuthInteractorLogConstants.RESET_PASSWORD_REQUEST, command.resetToken());
        String email = passwordResetTokenPort.extractEmailIfValid(command.resetToken());

        User user = userCommandRepository.findByEmail(email)
                                         .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        String encodedPassword = passwordEncoder.encode(command.password());
        user.changePassword(encodedPassword);
        userCommandRepository.save(user);
        logger.info(AuthInteractorLogConstants.RESET_PASSWORD_SUCCESS, email);
    }
}
