package com.adarsh.backend.shared.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.adarsh.backend.shared.domain.OtpToken;
import com.adarsh.backend.shared.infrastructure.persistence.entity.OtpEntity;

@Mapper(componentModel = "spring")
public interface OtpPersistenceMapper {
    OtpToken toDomain(OtpEntity entity);

    OtpEntity toEntity(OtpToken domain);
}
