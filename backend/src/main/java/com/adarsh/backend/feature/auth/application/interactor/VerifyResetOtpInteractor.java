package com.adarsh.backend.feature.auth.application.interactor;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpCommand;
import com.adarsh.backend.feature.auth.application.usecase.VerifyResetOtpUseCase;
import com.adarsh.backend.shared.application.port.OtpTokenRepository;
import com.adarsh.backend.shared.domain.OtpToken;
import com.adarsh.backend.shared.domain.exception.InvalidOtpException;
import com.adarsh.backend.shared.domain.exception.OtpExpiredException;
import com.adarsh.backend.shared.domain.exception.OtpNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerifyResetOtpInteractor implements VerifyResetOtpUseCase {
    private final OtpTokenRepository otpTokenRepository;

    @Override
    public String execute(VerifyResetOtpCommand command) {
        OtpToken otp = otpTokenRepository.getLatestOtpByEmail(command.getEmail())
                .orElseThrow(() -> new OtpNotFoundException("OTP not found"));

        if (otp.isExpired())
            throw new OtpExpiredException("OTP expired");
        if (!otp.getCode().equals(command.getCode())) {
            throw new InvalidOtpException("Invalid OTP");
        }
        String resetToken = UUID.randomUUID().toString();

        otp.generateResetToken(resetToken);
        otpTokenRepository.save(otp);
        return resetToken;
    }
}
