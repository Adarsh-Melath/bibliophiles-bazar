package com.adarsh.backend.feature.cart.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.cart.application.port.CartCommandRepositoryPort;
import com.adarsh.backend.feature.cart.application.port.CartQueryRepositoryPort;
import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.cart.infrastructure.persistence.entity.CartEntity;
import com.adarsh.backend.feature.cart.infrastructure.persistence.mapper.CartPersistenceMapper;
import com.adarsh.backend.feature.cart.infrastructure.persistence.jparepository.CartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartRepositoryAdapter implements CartCommandRepositoryPort, CartQueryRepositoryPort {

    private final CartJpaRepository cartJpaRepository;
    private final CartPersistenceMapper cartPersistenceMapper;

    @Override
    public Cart save(Cart cart) {
        CartEntity entity = cartPersistenceMapper.toEntity(cart);
        CartEntity savedEntity = cartJpaRepository.save(entity);
        return cartPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartJpaRepository.findByUserId(userId).map(cartPersistenceMapper::toDomain);
    }
}
