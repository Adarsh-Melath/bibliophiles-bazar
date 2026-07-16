package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.dto.command.RegisterUserCommand;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.application.usecase.RegisterUserUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.model.AuthProvider;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.shared.application.port.EmailPort;
import com.adarsh.backend.shared.application.port.OtpCachePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterUserInteractor implements RegisterUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(RegisterUserInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final OtpCachePort otpCachePort;
    private final EmailPort emailPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(RegisterUserCommand command) {
        logger.info(AuthInteractorLogConstants.CREATE_USER_REQUEST, command.email());
        if (userCommandRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException(AuthExceptionMessageConstants.EMAIL_ALREADY_EXISTS);
        }

        User user = new User.Builder()
                .name(command.name())
                .email(command.email())
                .password(passwordEncoder.encode(command.password())).role(Role.USER).provider(AuthProvider.LOCAL).build();

        User savedUser = userCommandRepository.save(user);
        logger.info(AuthInteractorLogConstants.CREATE_USER_SUCCESS, savedUser.getId());
        String code = otpCachePort.generateOtp(command.email());
        logger.info(AuthInteractorLogConstants.GENERATE_OTP, command.email());
        emailPort.sendOtp(command.email(), code);
        logger.info(AuthInteractorLogConstants.OTP_GENERATED, command.email());
    }
}
