package com.adarsh.backend.feature.auth.application.interactor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.auth.application.dto.ResetPasswordCommand;
import com.adarsh.backend.feature.auth.application.port.PasswordResetTokenPort;
import com.adarsh.backend.feature.auth.application.usecase.ResetPasswordUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ResetPasswordInteractor implements ResetPasswordUseCase {
    private final UserCommandRepository userCommandRepository;
    private final PasswordResetTokenPort passwordResetTokenPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(ResetPasswordCommand command) {
        String email = passwordResetTokenPort.extractEmailIfValid(command.getResetToken());

        User user = userCommandRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String encodedPassword = passwordEncoder.encode(command.getPassword());

        user.changePassword(encodedPassword);

        userCommandRepository.save(user);
    }
}
