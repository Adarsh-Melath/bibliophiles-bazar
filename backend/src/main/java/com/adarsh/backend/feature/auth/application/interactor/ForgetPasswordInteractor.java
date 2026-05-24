package com.adarsh.backend.feature.auth.application.interactor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.auth.application.dto.ForgetPasswordCommand;
import com.adarsh.backend.feature.auth.application.usecase.ForgetPasswordUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.EmailNotVerifiedException;
import com.adarsh.backend.feature.user.domain.exception.UserBlockedException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.port.EmailPort;
import com.adarsh.backend.shared.application.port.OtpTokenRepository;
import com.adarsh.backend.shared.domain.OtpGenerator;
import com.adarsh.backend.shared.domain.OtpToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ForgetPasswordInteractor implements ForgetPasswordUseCase {
    private final UserCommandRepository userCommandRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final EmailPort emailPort;

    @Override
    public void execute(ForgetPasswordCommand command) {
        User user = userCommandRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserNotFoundException("If this email is registered, you'll receive a reset code."));
        if (!user.isEnabled())
            throw new EmailNotVerifiedException("Please complete registration first");

        if (user.isBlocked())
            throw new UserBlockedException("Your account has been suspended. Contact support.");

        String code = OtpGenerator.generate6DigitOtp();

        OtpToken otp = new OtpToken.Builder()
                .email(command.getEmail())
                .code(code)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();

        otpTokenRepository.deleteOtpByEmail(command.getEmail());
        otpTokenRepository.save(otp);

        emailPort.sendOtp(command.getEmail(), code);
    }
}
