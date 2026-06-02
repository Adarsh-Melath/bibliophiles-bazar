package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.ChangePasswordCommand;

public interface ChangePasswordUseCase {
    void execute(String email,ChangePasswordCommand command);
}
