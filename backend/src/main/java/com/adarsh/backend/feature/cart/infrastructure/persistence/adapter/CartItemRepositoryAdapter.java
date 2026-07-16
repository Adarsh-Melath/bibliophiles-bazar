package com.adarsh.backend.feature.cart.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.cart.application.port.CartItemCommandRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartItemQueryRepositoryPort;
import com.adarsh.backend.feature.cart.domain.model.CartItem;
import com.adarsh.backend.feature.cart.infrastructure.persistence.mapper.CartItemPersistenceMapper;
import com.adarsh.backend.feature.cart.infrastructure.persistence.jparepository.CartItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartItemRepositoryAdapter implements CartItemCommandRepositoryPort, CartItemQueryRepositoryPort {

    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartItemPersistenceMapper cartItemPersistenceMapper;

    @Override
    public Optional<CartItem> findByCartIdAndBookId(Long cartId, Long bookId) {
        return cartItemJpaRepository.findByCartIdAndBookId(cartId, bookId).map(cartItemPersistenceMapper::toDomain);
    }

    @Override
    public Optional<CartItem> findByCartItemId(Long cartItemId) {
        return cartItemJpaRepository.findById(cartItemId).map(cartItemPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteByCartIdAndCartItemId(Long cartId, Long cartItemId) {
        cartItemJpaRepository.deleteByCartIdAndCartItemId(cartId, cartItemId);
    }
}
