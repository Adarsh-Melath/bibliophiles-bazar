package com.adarsh.backend.shared.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.adarsh.backend.shared.domain.Address;
import com.adarsh.backend.shared.infrastructure.persistence.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressPersistenceMapper {

    Address toDomain(AddressEntity addressEntity);

    AddressEntity toEntity(Address address);
}
