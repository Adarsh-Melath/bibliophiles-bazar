package com.adarsh.backend.feature.user.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
