package com.adarsh.backend.feature.returnrequest.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.returnrequest.domain.model.ReturnRequest;
import com.adarsh.backend.feature.returnrequest.infrastructure.persistence.entity.ReturnRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReturnRequestPersistenceMapper {

    ReturnRequestEntity toEntity(ReturnRequest domain);

    ReturnRequest toDomain(ReturnRequestEntity entity);
}
