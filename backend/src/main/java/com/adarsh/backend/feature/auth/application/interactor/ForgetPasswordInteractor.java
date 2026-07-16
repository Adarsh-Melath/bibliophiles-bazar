package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.dto.command.ForgetPasswordCommand;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.application.usecase.ForgetPasswordUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.EmailNotVerifiedException;
import com.adarsh.backend.feature.user.domain.exception.UserBlockedException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.shared.application.port.EmailPort;
import com.adarsh.backend.shared.application.port.OtpCachePort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ForgetPasswordInteractor implements ForgetPasswordUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ForgetPasswordInteractor.class);
    private final OtpCachePort otpCachePort;
    private final UserCommandRepository userCommandRepository;
    private final EmailPort emailPort;

    @Override
    public void execute(ForgetPasswordCommand command) {
        logger.info(AuthInteractorLogConstants.FORGOT_PASSWORD_REQUEST, command.email());
        User user = userCommandRepository.findByEmail(command.email())
                                         .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        if (!user.isEnabled())
            throw new EmailNotVerifiedException(AuthExceptionMessageConstants.EMAIL_NOT_VERIFIED);

        if (user.isBlocked())
            throw new UserBlockedException(AuthExceptionMessageConstants.USER_BLOCKED);

        logger.info(AuthInteractorLogConstants.GENERATE_OTP, command.email());
        String code = otpCachePort.generateOtp(command.email());
        logger.info(AuthInteractorLogConstants.OTP_GENERATED, command.email());
        emailPort.sendOtp(command.email(), code);
        logger.info(AuthInteractorLogConstants.FORGOT_PASSWORD_SUCCESS, command.email());
    }
}
