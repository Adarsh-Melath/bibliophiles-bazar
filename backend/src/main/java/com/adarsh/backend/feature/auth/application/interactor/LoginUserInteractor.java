package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.dto.command.LoginCommand;
import com.adarsh.backend.feature.auth.application.dto.result.LoginResult;
import com.adarsh.backend.feature.auth.application.dto.result.LoginUserResult;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.application.port.RefreshTokenRepository;
import com.adarsh.backend.feature.auth.application.usecase.LoginUseCase;
import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.domain.RefreshTokenGenerator;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.EmailNotVerifiedException;
import com.adarsh.backend.feature.user.domain.exception.InvalidCredentialException;
import com.adarsh.backend.feature.user.domain.exception.UserBlockedException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.port.JwtUtilPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserInteractor implements LoginUseCase {
    private static final Logger logger = LoggerFactory.getLogger(LoginUserInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilPort jwtUtilPort;

    @Override
    public LoginResult execute(LoginCommand command) {
        logger.info(AuthInteractorLogConstants.LOGIN_REQUEST, command.email());
        User user = userCommandRepository.findByEmail(command.email())
                                         .orElseThrow(() -> new UserNotFoundException(AuthExceptionMessageConstants.INVALID_CREDENTIALS));

        if (!user.isEnabled())
            throw new EmailNotVerifiedException(AuthExceptionMessageConstants.EMAIL_NOT_VERIFIED);

        if (user.isBlocked())
            throw new UserBlockedException(AuthExceptionMessageConstants.USER_BLOCKED);

        if (!passwordEncoder.matches(command.password(), user.getPassword()))
            throw new InvalidCredentialException(AuthExceptionMessageConstants.INVALID_CREDENTIALS);

        String accessToken = jwtUtilPort.generateAccessToken(command.email(), user.getRole().name());
        RefreshToken token = RefreshTokenGenerator.generateRefreshToken(command.email());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(token);

        LoginUserResult userResult = LoginUserResult.fromDomain(user);

        logger.info(AuthInteractorLogConstants.LOGIN_SUCCESS, user.getId());
        return new LoginResult(accessToken, savedRefreshToken.getToken(), userResult);
    }
}
