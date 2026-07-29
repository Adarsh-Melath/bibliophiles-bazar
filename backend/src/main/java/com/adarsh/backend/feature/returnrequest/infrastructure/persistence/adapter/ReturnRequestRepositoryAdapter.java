package com.adarsh.backend.feature.returnrequest.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.returnrequest.application.port.ReturnRequestCommandRepositoryPort;
import com.adarsh.backend.feature.returnrequest.domain.model.ReturnRequest;
import com.adarsh.backend.feature.returnrequest.infrastructure.persistence.entity.ReturnRequestEntity;
import com.adarsh.backend.feature.returnrequest.infrastructure.persistence.jparepository.ReturnRequestJpaRepository;
import com.adarsh.backend.feature.returnrequest.infrastructure.persistence.mapper.ReturnRequestPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReturnRequestRepositoryAdapter implements ReturnRequestCommandRepositoryPort {

    private final ReturnRequestJpaRepository returnRequestJpaRepository;
    private final ReturnRequestPersistenceMapper returnRequestPersistenceMapper;

    @Override
    public ReturnRequest save(ReturnRequest returnRequest) {
        ReturnRequestEntity entity = returnRequestPersistenceMapper.toEntity(returnRequest);
        ReturnRequestEntity savedEntity = returnRequestJpaRepository.save(entity);
        return returnRequestPersistenceMapper.toDomain(savedEntity);
    }
}
