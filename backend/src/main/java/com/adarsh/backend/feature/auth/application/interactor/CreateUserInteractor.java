package com.adarsh.backend.feature.auth.application.interactor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.auth.application.dto.CreateUserCommand;
import com.adarsh.backend.feature.auth.application.usecase.CreateUserUseCase;
import com.adarsh.backend.feature.auth.domain.OtpToken;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;

import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.port.EmailPort;
import com.adarsh.backend.shared.application.port.OtpTokenRepository;
import com.adarsh.backend.shared.domain.OtpGenerator;
import com.adarsh.backend.shared.domain.OtpToken;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateUserInteractor implements CreateUserUseCase {
    private final UserCommandRepository userCommandRepository;
    private final OtpTokenRepository otpTokenRepositoryPort;
    private final EmailPort emailPort;

    @Override
    public void execute(CreateUserCommand command) {
        if (userCommandRepository.existsByEmail(command.getEmail())) {
            throw new IllegalArgumentException("Email Already Exists");
        }

        User user = new User.Builder()
                .name(command.getName())
                .email(command.getEmail())
                .password(command.getPassword()).build();

        userCommandRepository.save(user);
        String code = OtpGenerator.generate6DigitOtp();

        OtpToken otp = new OtpToken.Builder()
                .email(command.getEmail())
                .code(code)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();

        otpTokenRepositoryPort.deleteOtpByEmail(command.getEmail());
        otpTokenRepositoryPort.save(otp);

        emailPort.sendOtp(command.getEmail(), code);
    }
}
