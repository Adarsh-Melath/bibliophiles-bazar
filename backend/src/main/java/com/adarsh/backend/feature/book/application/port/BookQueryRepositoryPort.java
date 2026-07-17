package com.adarsh.backend.feature.book.application.port;

import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.Optional;

public interface BookQueryRepositoryPort {
    Optional<Book> findBySlug(String slug);

    PageResult<Book> searchPublisherBooks(PublisherBookSearchCriteria criteria, PageQuery result);

    PageResult<Book> searchPublisherBooksById(Long publisherId, String keyword, PageQuery query);

    PageResult<Book> searchPublishedBooks(PublishedBookSearchCriteria criteria, PageQuery query);
}
