package com.adarsh.backend.feature.user.application.interactor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.dto.ChangePasswordCommand;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.usecase.ChangePasswordUseCase;
import com.adarsh.backend.feature.user.domain.exception.PasswordIncorrectException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChangePasswordInteractor implements ChangePasswordUseCase {
    private final PasswordEncoder passwordEncoder;
    private final UserCommandRepository userCommandRepository;

    @Override
    public void execute(String email,ChangePasswordCommand command){
        User user=userCommandRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User Not Found"));

        if(!passwordEncoder.matches(command.getCurrentPassword(),user.getPassword())){
            throw new PasswordIncorrectException("Current Password is incorrect");
        }

        user.changePassword(passwordEncoder.encode(command.getNewPassword()));
        userCommandRepository.save(user);
    }
}
