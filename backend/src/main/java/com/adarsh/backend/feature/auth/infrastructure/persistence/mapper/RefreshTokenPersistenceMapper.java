package com.adarsh.backend.feature.auth.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.infrastructure.persistence.entity.RefreshTokenEntity;

@Mapper(componentModel = "spring")
public interface RefreshTokenPersistenceMapper {
    RefreshToken toDomain(RefreshTokenEntity entity);

    RefreshTokenEntity toEntity(RefreshToken domain);
}
