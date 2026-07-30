package com.adarsh.backend.feature.discount.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferPersistenceMapper {

    OfferEntity toEntity(Offer domain);

    @Mapping(target = "isActive", source = "active")
    Offer toDomain(OfferEntity entity);
}