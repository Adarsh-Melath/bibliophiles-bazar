package com.adarsh.backend.feature.auth.application.interactor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.auth.application.dto.LoginCommand;
import com.adarsh.backend.feature.auth.application.dto.LoginResult;
import com.adarsh.backend.feature.auth.application.dto.LoginUserResult;
import com.adarsh.backend.feature.auth.application.usecase.LoginUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.EmailNotVerifiedException;
import com.adarsh.backend.feature.user.domain.exception.InvalidCredentialException;
import com.adarsh.backend.feature.user.domain.exception.UserBlockedException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.port.JwtUtilPort;
import com.adarsh.backend.shared.application.port.RefreshTokenRepository;
import com.adarsh.backend.shared.domain.RefreshToken;
import com.adarsh.backend.shared.domain.RefreshTokenGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserInteractor implements LoginUseCase {
    private final UserCommandRepository userCommandRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilPort jwtUtilPort;

    @Override
    public LoginResult execute(LoginCommand command) {
        User user = userCommandRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password."));

        if (!user.isEnabled())
            throw new EmailNotVerifiedException("Your account has been blocked");

        if (user.isBlocked())
            throw new UserBlockedException("Your account has been blocked");

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword()))
            throw new InvalidCredentialException("Invalid Credentials");

        String accessToken = jwtUtilPort.generateAccessToken(command.getEmail(), user.getRole().name());
        RefreshToken token = RefreshTokenGenerator.generateRefreshToken(command.getEmail());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(token);

        LoginUserResult userResult=LoginUserResult.fromDomain(user);

        return new LoginResult(accessToken, savedRefreshToken.getToken(), userResult);
    }
}
