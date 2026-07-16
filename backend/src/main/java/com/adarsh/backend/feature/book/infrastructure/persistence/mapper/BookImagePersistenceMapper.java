package com.adarsh.backend.feature.book.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.book.domain.model.BookImage;
import com.adarsh.backend.feature.book.infrastructure.persistence.entity.BookImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookImagePersistenceMapper {
    BookImage toDomain(BookImageEntity entity);

    BookImageEntity toEntity(BookImage domain);
}
