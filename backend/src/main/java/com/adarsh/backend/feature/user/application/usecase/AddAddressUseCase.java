package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.AddAddressResult;
import com.adarsh.backend.feature.user.application.dto.AddAddressCommand;
public interface AddAddressUseCase {
    AddAddressResult execute(String email,AddAddressCommand command);
}
