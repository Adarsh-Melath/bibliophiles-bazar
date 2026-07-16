package com.adarsh.backend.feature.cart.application.interactor;

import com.adarsh.backend.feature.cart.application.dto.result.GetCartItemsResult;
import com.adarsh.backend.feature.cart.application.interactor.constant.CartInteractorLogConstants;
import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.application.usecase.GetCartItemsUseCase;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCartItemsInteractor implements GetCartItemsUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GetCartItemsInteractor.class);

    private final UserCommandRepository userCommandRepository;
    private final CartQueryRepositoryPort cartQueryRepositoryPort;

    @Override
    public List<GetCartItemsResult> execute(String email) {
        logger.info(CartInteractorLogConstants.GET_CART_ITEMS_REQUEST, email);

        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Cart cart = cartQueryRepositoryPort.findByUserId(user.getId()).orElse(null);

        if (cart == null || cart.getItems() == null) {
            logger.info(CartInteractorLogConstants.GET_CART_ITEMS_FETCHED, 0);
            return Collections.emptyList();
        }

        logger.debug(CartInteractorLogConstants.GET_CART_ITEMS_CART_FOUND, cart.getId(), user.getId());

        List<GetCartItemsResult> results = cart.getItems().stream().map(GetCartItemsResult::fromDomain).collect(Collectors.toList());

        logger.info(CartInteractorLogConstants.GET_CART_ITEMS_FETCHED, results.size());
        return results;
    }
}
