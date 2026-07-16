package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.dto.result.RefreshAccessTokenResult;
import com.adarsh.backend.feature.auth.application.dto.result.RefreshAccessTokenUserResult;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.application.port.RefreshTokenRepository;
import com.adarsh.backend.feature.auth.application.usecase.RefreshAccessTokenUseCase;
import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.domain.exception.RefreshTokenExpiredException;
import com.adarsh.backend.feature.auth.domain.exception.RefreshTokenNotFoundException;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.shared.application.port.JwtUtilPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshAccessTokenInteractor implements RefreshAccessTokenUseCase {
    private static final Logger logger = LoggerFactory.getLogger(RefreshAccessTokenInteractor.class);
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserCommandRepository userCommandRepository;
    private final JwtUtilPort jwtUtilPort;

    @Override
    public RefreshAccessTokenResult execute(String refreshToken) {
        logger.info(AuthInteractorLogConstants.REFRESH_ACCESS_TOKEN_REQUEST);
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                                                   .orElseThrow(() -> new RefreshTokenNotFoundException(AuthExceptionMessageConstants.REFRESH_TOKEN_NOT_FOUND));

        if (token.isExpired()) {
            throw new RefreshTokenExpiredException(AuthExceptionMessageConstants.REFRESH_TOKEN_EXPIRED);
        }

        User user = userCommandRepository.findByEmail(token.getEmail())
                                         .orElseThrow(() -> new UserNotFoundException(AuthExceptionMessageConstants.TOKEN_USER_NOT_FOUND));

        String accessToken = jwtUtilPort.generateAccessToken(token.getEmail(), user.getRole().name());

        RefreshAccessTokenUserResult userResult = RefreshAccessTokenUserResult.fromDomain(user);

        logger.info(AuthInteractorLogConstants.REFRESH_ACCESS_TOKEN_SUCCESS);
        return new RefreshAccessTokenResult(accessToken, refreshToken, userResult);
    }
}