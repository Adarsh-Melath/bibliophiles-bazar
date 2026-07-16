package com.adarsh.backend.feature.user.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.user.domain.model.Address;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressPersistenceMapper {
    @Mapping(source = "user.id", target = "userId")
    Address toDomain(AddressEntity addressEntity);

    @Mapping(source = "userId", target = "user.id")
    AddressEntity toEntity(Address address);
}
