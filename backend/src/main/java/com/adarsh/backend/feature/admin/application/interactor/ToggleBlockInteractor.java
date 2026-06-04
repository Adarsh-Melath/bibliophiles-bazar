package com.adarsh.backend.feature.admin.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.admin.application.usecase.ToggleBlockUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.AdminCannotBeBlockedException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToggleBlockInteractor implements ToggleBlockUseCase {

    private final UserCommandRepository userCommandRepository;

    @Override
    public void execute(Long userId) {
        User user = userCommandRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (user.getRole().name().equals("ADMIN")) {
            throw new AdminCannotBeBlockedException("Admin cannot be blocked");
        }

        if (user.isBlocked()) {
            user.unblock();
        } else {
            user.block();
        }

        userCommandRepository.save(user);
    }
}
