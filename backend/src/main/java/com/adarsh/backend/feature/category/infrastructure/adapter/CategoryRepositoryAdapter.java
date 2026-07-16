package com.adarsh.backend.feature.category.infrastructure.adapter;

import com.adarsh.backend.feature.category.application.port.CategoryCommandPort;
import com.adarsh.backend.feature.category.application.port.CategoryQueryPort;
import com.adarsh.backend.feature.category.application.port.CategorySearchCriteria;
import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.category.domain.model.CategoryType;
import com.adarsh.backend.feature.category.infrastructure.entity.CategoryEntity;
import com.adarsh.backend.feature.category.infrastructure.jparepository.CategoryJpaRepository;
import com.adarsh.backend.feature.category.infrastructure.mapper.CategoryPersistenceMapper;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
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
public class CategoryRepositoryAdapter implements CategoryCommandPort, CategoryQueryPort {
    private final CategoryPersistenceMapper mapper;
    private final CategoryJpaRepository jpa;

    @Override
    public Category save(Category categoryDomain) {
        CategoryEntity categoryEntity = mapper.toEntity(categoryDomain);
        jpa.save(categoryEntity);
        return mapper.toDomain(categoryEntity);
    }

    @Override
    public boolean existsByCategoryType(CategoryType type) {
        return jpa.existsByType(type);
    }

    @Override
    public Optional<Category> findBySlug(String slug) {
        Optional<CategoryEntity> categoryEntity = jpa.findBySlug(slug);
        return categoryEntity.map(mapper::toDomain);
    }

    @Override
    public PageResult<Category> search(CategorySearchCriteria criteria, PageQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), Sort.by("createdAt").descending());
        String searchKeyword = (criteria.keyword() == null || criteria.keyword().trim().isEmpty()) ? null : criteria.keyword();
        Page<CategoryEntity> springPage = jpa.searchCategories(searchKeyword, pageable);
        List<Category> domainCategories = springPage.getContent().stream().map(mapper::toDomain).toList();

        return new PageResult<>(domainCategories, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

}
