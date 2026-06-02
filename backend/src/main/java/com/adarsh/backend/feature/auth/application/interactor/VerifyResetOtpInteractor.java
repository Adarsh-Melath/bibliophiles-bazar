package com.adarsh.backend.feature.auth.application.interactor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpResult;
import com.adarsh.backend.feature.auth.application.port.PasswordResetTokenPort;
import com.adarsh.backend.feature.auth.application.usecase.VerifyResetOtpUseCase;
import com.adarsh.backend.shared.application.port.OtpTokenRepository;
import com.adarsh.backend.shared.domain.OtpToken;
import com.adarsh.backend.shared.domain.exception.InvalidOtpException;
import com.adarsh.backend.shared.domain.exception.OtpExpiredException;
import com.adarsh.backend.shared.domain.exception.OtpNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VerifyResetOtpInteractor implements VerifyResetOtpUseCase {
    private final OtpTokenRepository otpTokenRepository;
    private final PasswordResetTokenPort passwordResetTokenPort;

    @Override
    public VerifyResetOtpResult execute(VerifyResetOtpCommand command) {
        OtpToken otp = otpTokenRepository.getLatestOtpByEmail(command.getEmail())
                .orElseThrow(() -> new OtpNotFoundException("OTP not found"));

        if (otp.isExpired())
            throw new OtpExpiredException("OTP expired");
        if (!otp.getCode().equals(command.getCode())) {
            throw new InvalidOtpException("Invalid OTP");
        }

        // 2. Cleanup: Delete the OTP so it cannot be reused
        otpTokenRepository.deleteOtpByEmail(command.getEmail());

        // 3. Generate the secure JWT for the next step
        String resetJwt = passwordResetTokenPort.generateResetToken(command.getEmail());
        return new VerifyResetOtpResult(resetJwt);
    }
}
