package com.adarsh.backend.shared.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.dto.VerifiedUserResult;
import com.adarsh.backend.shared.application.dto.VerifyOtpCommand;
import com.adarsh.backend.shared.application.dto.VerifyOtpResult;
import com.adarsh.backend.shared.application.port.JwtUtilPort;
import com.adarsh.backend.shared.application.port.OtpTokenRepository;
import com.adarsh.backend.shared.application.port.RefreshTokenRepository;
import com.adarsh.backend.shared.application.usecase.VerifyOtpUseCase;
import com.adarsh.backend.shared.domain.OtpToken;
import com.adarsh.backend.shared.domain.RefreshToken;
import com.adarsh.backend.shared.domain.RefreshTokenGenerator;
import com.adarsh.backend.shared.domain.exception.InvalidOtpException;
import com.adarsh.backend.shared.domain.exception.OtpExpiredException;
import com.adarsh.backend.shared.domain.exception.OtpNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerifyOtpInteractor implements VerifyOtpUseCase {
    private final OtpTokenRepository otpRepositoryPort;
    private final UserCommandRepository userCommandRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtilPort jwtUtilPort;

    @Override
    public VerifyOtpResult execute(VerifyOtpCommand command) {
        OtpToken otp = otpRepositoryPort.getLatestOtpByEmail(command.getEmail())
                .orElseThrow(() -> new OtpNotFoundException("OTP not found"));

        if (otp.isExpired())
            throw new OtpExpiredException("OTP expired");
        if (!otp.getCode().equals(command.getCode()))
            throw new InvalidOtpException("Invalid OTP");

        User user = userCommandRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.enableAccount();
        userCommandRepository.save(user);
        otpRepositoryPort.deleteOtpByEmail(command.getEmail());

        String accessToken = jwtUtilPort.generateAccessToken(command.getEmail(), user.getRole().name());
        RefreshToken token = RefreshTokenGenerator.generateRefreshToken(command.getEmail());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(token);

        VerifiedUserResult userResult = VerifiedUserResult.fromDomain(user);

        return new VerifyOtpResult(accessToken, savedRefreshToken.getToken(), userResult);
    }
}
