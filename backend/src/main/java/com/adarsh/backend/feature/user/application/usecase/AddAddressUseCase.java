package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.command.AddAddressCommand;
import com.adarsh.backend.feature.user.application.dto.result.AddAddressResult;

public interface AddAddressUseCase {
    AddAddressResult execute(String email, AddAddressCommand command);
}
