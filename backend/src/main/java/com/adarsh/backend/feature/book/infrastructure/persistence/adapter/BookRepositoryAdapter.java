package com.adarsh.backend.feature.book.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.book.application.port.BookCommandRepositoryPort;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.port.PublishedBookSearchCriteria;
import com.adarsh.backend.feature.book.application.port.PublisherBookSearchCriteria;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.book.domain.model.SortOption;
import com.adarsh.backend.feature.book.infrastructure.persistence.entity.BookEntity;
import com.adarsh.backend.feature.book.infrastructure.persistence.jpaRepository.BookJpaRepository;
import com.adarsh.backend.feature.book.infrastructure.persistence.jpaRepository.BookJpaSpecification;
import com.adarsh.backend.feature.book.infrastructure.persistence.mapper.BookPersistenceMapper;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookCommandRepositoryPort, BookQueryRepositoryPort {
    private final BookPersistenceMapper bookPersistenceMapper;
    private final BookJpaRepository bookJpaRepository;

    @Override
    public Book save(Book book) {
        BookEntity bookEntity = bookPersistenceMapper.toEntityWithImages(book);
        BookEntity savedBookEntity = bookJpaRepository.save(bookEntity);
        return bookPersistenceMapper.toDomain(savedBookEntity);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookJpaRepository.existsByIsbn(isbn);
    }

    @Override
    public Optional<Book> findBySlug(String slug) {
        return bookJpaRepository.findBySlug(slug).map(bookPersistenceMapper::toDomain);
    }

    @Override
    public PageResult<Book> searchPublisherBooks(PublisherBookSearchCriteria criteria, PageQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), Sort.by("createdAt").descending());

        Page<BookEntity> springPage = bookJpaRepository.searchBooksAndDeletedFalse(criteria.keyword(), pageable);
        List<Book> domainBook = springPage.getContent().stream().map(bookPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainBook, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    @Override
    public PageResult<Book> searchPublisherBooksById(Long publisherId, String keyword, PageQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), Sort.by("createdAt").descending());

        Page<BookEntity> springPage = bookJpaRepository.findByPublisherIdAndDeletedFalse(publisherId, keyword, pageable);
        List<Book> domainBook = springPage.getContent().stream().map(bookPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainBook, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    @Override
    public PageResult<Book> searchPublishedBooks(PublishedBookSearchCriteria criteria, PageQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size(), toSpringSort(criteria.sortOption()));

        Specification<BookEntity> spec = Specification.where(BookJpaSpecification.keywordContains(criteria.keyword()))
                .and(BookJpaSpecification.categoryNameEquals(criteria.type() != null ? criteria.type().name() : null))
                .and(BookJpaSpecification.priceGreaterThanOrEqual(criteria.minPrice()))
                .and(BookJpaSpecification.priceLessThaOrEqual(criteria.maxPrice()));

        Page<BookEntity> springPage = bookJpaRepository.findAll(spec, pageable);
        List<Book> domainBook = springPage.getContent().stream().map(bookPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainBook, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    private Sort toSpringSort(SortOption sort) {
        return switch (sort) {
            case PRICE_ASC -> Sort.by("price").ascending();
            case PRICE_DESC -> Sort.by("price").descending();
            case TITLE_ASC -> Sort.by("title").ascending();
            case TITLE_DESC -> Sort.by("title").descending();
            case NEWEST -> Sort.by("createdAt").descending();
            case OLDEST -> Sort.by("createdAt").ascending();
        };
    }
}