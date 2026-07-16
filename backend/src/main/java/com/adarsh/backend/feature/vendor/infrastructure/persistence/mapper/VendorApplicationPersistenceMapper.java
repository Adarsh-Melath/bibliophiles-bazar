package com.adarsh.backend.feature.vendor.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.entity.VendorApplicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorApplicationPersistenceMapper {
    VendorApplication toDomain(VendorApplicationEntity entity);

    VendorApplicationEntity toEntity(VendorApplication domain);
}
