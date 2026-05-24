package com.adarsh.backend.feature.user.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.UserEntity;

@Mapper
public interface UserPersistenceMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
