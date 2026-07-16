package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.port.PublishedBookSearchCriteria;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.book.domain.model.SortOption;
import com.adarsh.backend.feature.category.domain.model.CategoryType;
import com.adarsh.backend.feature.user.application.dto.result.GetPublishedBooksResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.usecase.GetPublishedBooksUseCase;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPublishedBooksInteractor implements GetPublishedBooksUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetPublishedBooksInteractor.class);
    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    public PageResult<GetPublishedBooksResult> execute(String keyword, SortOption sortOption, CategoryType type, Double minPrice, Double maxPrice, int page, int size) {
        logger.info(UserInteractorLogConstants.GET_PUBLISHED_BOOKS_REQUEST, keyword);
        PageQuery query = new PageQuery(page, size);
        PublishedBookSearchCriteria criteria = new PublishedBookSearchCriteria(keyword, sortOption, type, minPrice, maxPrice, page, size);

        PageResult<Book> domainPages = bookQueryRepositoryPort.searchPublishedBooks(criteria, query);
        logger.info(UserInteractorLogConstants.GET_PUBLISHED_BOOKS_FETCHED, domainPages.totalElements());

        return domainPages.map(GetPublishedBooksResult::fromDomain);
    }
}
