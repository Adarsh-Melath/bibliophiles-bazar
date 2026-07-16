package com.adarsh.backend.feature.category.infrastructure.mapper;

import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.category.infrastructure.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {
    Category toDomain(CategoryEntity entity);

    CategoryEntity toEntity(Category domain);
}
