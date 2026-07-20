package com.adarsh.backend.feature.wishlist.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.wishlist.application.port.WishlistCommandRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.port.WishlistQueryRepositoryPort;
import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.entity.WishlistEntity;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.jparepository.WishlistJpaRepository;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.mapper.WishlistPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WishlistRepositoryAdapter implements WishlistCommandRepositoryPort, WishlistQueryRepositoryPort {

    private final WishlistJpaRepository wishlistJpaRepository;
    private final WishlistPersistenceMapper wishlistPersistenceMapper;

    @Override
    public Wishlist save(Wishlist wishlist) {
        WishlistEntity entity = wishlistPersistenceMapper.toEntityWithItems(wishlist);
        WishlistEntity savedEntity = wishlistJpaRepository.save(entity);
        return wishlistPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Wishlist> findByUserId(Long userId) {
        return wishlistJpaRepository.findByUserId(userId).map(wishlistPersistenceMapper::toDomain);
    }
}
