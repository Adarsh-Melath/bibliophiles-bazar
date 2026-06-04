package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.dto.UpdateAddressResult;
public interface UpdateAddressUseCase {
    UpdateAddressResult   execute(String email, Long id, UpdateAddressCommand command);
}
