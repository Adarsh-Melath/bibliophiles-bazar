package com.adarsh.backend.feature.auth.application.interactor;

import com.adarsh.backend.feature.auth.application.dto.command.VerifyResetOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.result.VerifyResetOtpResult;
import com.adarsh.backend.feature.auth.application.interactor.constant.AuthInteractorLogConstants;
import com.adarsh.backend.feature.auth.application.port.PasswordResetTokenPort;
import com.adarsh.backend.feature.auth.application.usecase.VerifyResetOtpUseCase;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.shared.application.port.OtpCachePort;
import com.adarsh.backend.feature.auth.domain.exception.InvalidOtpException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VerifyResetOtpInteractor implements VerifyResetOtpUseCase {
    private static final Logger logger = LoggerFactory.getLogger(VerifyResetOtpInteractor.class);
    private final OtpCachePort otpCachePort;
    private final PasswordResetTokenPort passwordResetTokenPort;

    @Override
    public VerifyResetOtpResult execute(VerifyResetOtpCommand command) {
        logger.info(AuthInteractorLogConstants.VERIFY_RESET_OTP_REQUEST, command.email());
        if (otpCachePort.verifyOtp(command.email(), command.code())) {
            throw new InvalidOtpException(AuthExceptionMessageConstants.INVALID_OTP);
        }

        String resetJwt = passwordResetTokenPort.generateResetToken(command.email());
        logger.info(AuthInteractorLogConstants.VERIFY_RESET_OTP_SUCCESS, command.email());
        return new VerifyResetOtpResult(resetJwt);
    }
}
