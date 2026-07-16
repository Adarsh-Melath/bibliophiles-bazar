package com.adarsh.backend.feature.vendor.infrastructure.persistence.adapter;

import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationCommandRepositoryPort;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationQueryRepositoryPort;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationSearchCriteria;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.entity.VendorApplicationEntity;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.jparepository.VendorApplicationJpaRepository;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.mapper.VendorApplicationPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VendorApplicationRepositoryPortAdapter implements VendorApplicationCommandRepositoryPort, VendorApplicationQueryRepositoryPort {
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
    public Optional<VendorApplication> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public PageResult<VendorApplication> search(VendorApplicationSearchCriteria criteria, PageQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), Sort.by("appliedAt").descending());

        // pass the exact criteria fields to our new dynamic @Query
        Page<VendorApplicationEntity> springPage = jpaRepository.searchVendorApplication(criteria.keyword(), criteria.status(), pageable);
        List<VendorApplication> domainApplication = springPage.getContent().stream().map(mapper::toDomain).toList();

        return new PageResult<>(domainApplication, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }
}
