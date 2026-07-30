package com.adarsh.backend.feature.discount.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.CouponEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponPersistenceMapper {

    CouponEntity toEntity(Coupon domain);

    @Mapping(target = "isActive", source = "active")
    Coupon toDomain(CouponEntity entity);
}