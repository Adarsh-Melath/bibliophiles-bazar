package com.adarsh.backend.feature.cart.application.usecase;

import com.adarsh.backend.feature.cart.application.dto.command.UpdateCartItemCommand;
import com.adarsh.backend.feature.cart.application.dto.result.UpdateCartItemResult;

public interface UpdateCartItemUseCase {
    UpdateCartItemResult updateCartItem(String userEmail, UpdateCartItemCommand command);
}
