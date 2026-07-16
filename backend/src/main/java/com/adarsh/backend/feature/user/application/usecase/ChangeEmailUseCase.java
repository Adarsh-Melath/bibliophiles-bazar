package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.command.ChangeEmailCommand;

public interface ChangeEmailUseCase {
    void execute(String email, ChangeEmailCommand command);
}
