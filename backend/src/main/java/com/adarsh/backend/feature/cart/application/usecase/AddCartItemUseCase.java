package com.adarsh.backend.feature.cart.application.usecase;

import com.adarsh.backend.feature.cart.application.dto.command.AddCartItemCommand;
import com.adarsh.backend.feature.cart.application.dto.result.AddCartItemResult;

public interface AddCartItemUseCase {
    AddCartItemResult execute(String email, AddCartItemCommand command);
}
