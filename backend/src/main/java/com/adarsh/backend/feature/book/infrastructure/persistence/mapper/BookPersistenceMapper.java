package com.adarsh.backend.feature.book.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.book.infrastructure.persistence.entity.BookEntity;
import com.adarsh.backend.feature.book.infrastructure.persistence.entity.BookImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookImagePersistenceMapper.class})
public interface BookPersistenceMapper {
    Book toDomain(BookEntity entity);

    @Mapping(target = "images", ignore = true)
    BookEntity toEntity(Book domain);

    default BookEntity toEntityWithImages(Book domain) {
        BookEntity entity = toEntity(domain);
        
        if (domain.getImages() != null && !domain.getImages().isEmpty()) {
            entity.getImages().clear();
            domain.getImages().forEach(image -> {
                BookImageEntity imageEntity = new BookImageEntity();
                imageEntity.setUrl(image.getUrl());
                imageEntity.setBook(entity);
                entity.getImages().add(imageEntity);
            });
        }
        
        return entity;
    }
}
