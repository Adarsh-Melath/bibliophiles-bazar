package com.adarsh.backend.feature.vendor.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.entity.VendorApplicationEntity;

@Mapper(componentModel = "spring")
public interface VendorApplicationPersistenceMapper {
    VendorApplication toDomain(VendorApplicationEntity entity);

    VendorApplicationEntity toEntity(VendorApplication domain);
}
