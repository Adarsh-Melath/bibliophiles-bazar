package com.adarsh.backend.feature.auth.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.infrastructure.persistence.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenPersistenceMapper {
    RefreshToken toDomain(RefreshTokenEntity entity);

    RefreshTokenEntity toEntity(RefreshToken domain);
}
