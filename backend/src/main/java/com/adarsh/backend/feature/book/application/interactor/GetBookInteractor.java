package com.adarsh.backend.feature.book.application.interactor;

import com.adarsh.backend.feature.book.application.dto.result.GetBooksResult;
import com.adarsh.backend.feature.book.application.interactor.constant.BookInteractorLogConstants;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.port.PublisherBookSearchCriteria;
import com.adarsh.backend.feature.book.application.usecase.GetBooksUseCase;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
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
    private final UserCommandRepository userCommandRepository;

    @Override
    public PageResult<GetBooksResult> execute(String keyword, int page, int size) {
        logger.info(BookInteractorLogConstants.GET_BOOKS_REQUEST, keyword, page, size);

        PageQuery query = new PageQuery(page, size);
        PublisherBookSearchCriteria criteria = new PublisherBookSearchCriteria(keyword);
        PageResult<Book> domainBook = bookQueryRepositoryPort.searchPublisherBooks(criteria, query);

        logger.info(BookInteractorLogConstants.GET_BOOKS_FETCHED, domainBook.totalElements());
        return domainBook.map(GetBooksResult::fromDomain);
    }
}
