package com.adarsh.backend.feature.vendor.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adarsh.backend.feature.vendor.application.port.VendorRepositoryPort;
import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.entity.VendorApplicationEntity;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.jparepository.VendorApplicationJpaRepository;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.mapper.VendorApplicationPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VendorApplicationRepositoryAdapter implements VendorRepositoryPort {
    private final VendorApplicationJpaRepository jpaRepository;
    private final VendorApplicationPersistenceMapper mapper;

    @Override
    public VendorApplication save(VendorApplication application) {
        VendorApplicationEntity applicationEntity = mapper.toEntity(application);
        jpaRepository.save(applicationEntity);
        return mapper.toDomain(applicationEntity);
    }

    @Override
    public Optional<VendorApplication> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<VendorApplication> findAll() {
        List<VendorApplicationEntity> entities = jpaRepository.findAll();
        return entities.stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public List<VendorApplication> findByStatus(ApplicationStatus status){
        List<VendorApplicationEntity> entities = jpaRepository.findByStatus(status);
        return entities.stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<VendorApplication> findByEmail(String email){
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }
}
