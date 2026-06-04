package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.UpdateAddressResult;
import com.adarsh.backend.feature.user.application.dto.command.UpdateAddressCommand;

public interface UpdateAddressUseCase {
    UpdateAddressResult   execute(String email, Long id, UpdateAddressCommand command);
}
