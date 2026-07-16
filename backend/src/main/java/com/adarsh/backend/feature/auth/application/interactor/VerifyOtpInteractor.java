package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.port.RefreshTokenRepository;
import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.domain.RefreshTokenGenerator;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.auth.application.dto.result.VerifiedUserResult;
import com.adarsh.backend.feature.auth.application.dto.command.VerifyOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.result.VerifyOtpResult;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.shared.application.port.JwtUtilPort;
import com.adarsh.backend.shared.application.port.OtpCachePort;
import com.adarsh.backend.feature.auth.application.usecase.VerifyOtpUseCase;
import com.adarsh.backend.feature.auth.domain.exception.InvalidOtpException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class VerifyOtpInteractor implements VerifyOtpUseCase {
    private static final Logger logger =
            org.slf4j.LoggerFactory.getLogger(VerifyOtpInteractor.class);

    private final OtpCachePort otpCachePort;
    private final UserCommandRepository userCommandRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtilPort jwtUtilPort;

    @Override
    public VerifyOtpResult execute(VerifyOtpCommand command) {
        logger.info(AuthInteractorLogConstants.VERIFY_OTP_REQUEST, command.email());

        if (otpCachePort.verifyOtp(command.email(), command.code())) {
            throw new InvalidOtpException(AuthExceptionMessageConstants.INVALID_OTP);
        }

        logger.info(AuthInteractorLogConstants.VERIFY_OTP_VALIDATED, command.email());

        User user = userCommandRepository.findByEmail(command.email())
                                         .orElseThrow(() -> new UserNotFoundException(
                                                 UserExceptionMessageConstants.USER_NOT_FOUND));
        user.enableAccount();
        userCommandRepository.save(user);

        logger.info(AuthInteractorLogConstants.VERIFY_OTP_USER_ENABLED, user.getId());

        String accessToken = jwtUtilPort.generateAccessToken(command.email(), user.getRole().name());
        RefreshToken token = RefreshTokenGenerator.generateRefreshToken(command.email());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(token);

        VerifiedUserResult userResult = VerifiedUserResult.fromDomain(user);

        logger.info(AuthInteractorLogConstants.VERIFY_OTP_SUCCESS, command.email());

        return new VerifyOtpResult(accessToken, savedRefreshToken.getToken(), userResult);
    }
}
