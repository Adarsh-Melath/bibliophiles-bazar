package com.adarsh.backend.feature.user.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.port.UserSearchCriteria;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.pagination.PageQuery;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.UserEntity;
import com.adarsh.backend.feature.user.infrastructure.persistence.jparepository.UserJpaRepository;
import com.adarsh.backend.feature.user.infrastructure.persistence.mapper.UserPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserCommandRepository, UserQueryRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        UserEntity savedEntity = userJpaRepository.save(mapper.toEntity(user));
        return mapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public PageResult<User> search(UserSearchCriteria criteria, PageQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("createdAt").descending());

        // 1. Pass the exact criteria fields to our new dynamic @Query
        Page<UserEntity> springPage = userJpaRepository.searchUsers(
                criteria.searchKeyword(),
                criteria.role(),
                criteria.enabled(),
                pageable
        );

        // 2. Map Entity to Domain
        List<User> domainUsers = springPage.getContent().stream().map(mapper::toDomain).toList();

        // 3. Return Pure Java PageResult
        return new PageResult<>(domainUsers, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }
}
