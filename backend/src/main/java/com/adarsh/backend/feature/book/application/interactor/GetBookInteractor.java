package com.adarsh.backend.feature.book.application.interactor;

import com.adarsh.backend.feature.book.application.dto.result.GetBooksResult;
import com.adarsh.backend.feature.book.application.interactor.constant.BookInteractorLogConstants;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.port.PublicBookSearchCriteria;
import com.adarsh.backend.feature.book.application.port.PublishedBookSearchCriteria;
import com.adarsh.backend.feature.book.application.usecase.GetBooksUseCase;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookInteractor implements GetBooksUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetBookInteractor.class);

    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    public PageResult<GetBooksResult> execute(PublicBookSearchCriteria criteria) {
        logger.info(BookInteractorLogConstants.GET_BOOKS_REQUEST, criteria.keyword(), criteria.page(), criteria.size());

        PageQuery query = new PageQuery(criteria.page(), criteria.size());
        PublishedBookSearchCriteria publishedCriteria = new PublishedBookSearchCriteria(
                criteria.keyword(),
                criteria.sortOption(),
                criteria.type(),
                criteria.minPrice(),
                criteria.maxPrice(),
                criteria.page(),
                criteria.size()
        );
        PageResult<Book> domainBook = bookQueryRepositoryPort.searchPublishedBooks(publishedCriteria, query);

        logger.info(BookInteractorLogConstants.GET_BOOKS_FETCHED, domainBook.totalElements());
        return domainBook.map(GetBooksResult::fromDomain);
    }
}
