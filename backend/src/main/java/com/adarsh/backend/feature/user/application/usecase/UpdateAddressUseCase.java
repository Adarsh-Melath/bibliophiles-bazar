package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.command.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.dto.result.UpdateAddressResult;

public interface UpdateAddressUseCase {
    UpdateAddressResult execute(String email, Long id, UpdateAddressCommand command);
}
