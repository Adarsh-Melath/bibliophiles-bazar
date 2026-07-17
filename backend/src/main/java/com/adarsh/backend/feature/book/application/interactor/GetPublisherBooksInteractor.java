package com.adarsh.backend.feature.book.application.interactor;

import com.adarsh.backend.feature.book.application.dto.result.GetBooksResult;
import com.adarsh.backend.feature.book.application.interactor.constant.BookInteractorLogConstants;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.usecase.GetPublisherBooksUseCase;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPublisherBooksInteractor implements GetPublisherBooksUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetPublisherBooksInteractor.class);

    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final UserCommandRepository userCommandRepository;

    @Override
    public PageResult<GetBooksResult> execute(String email, String keyword, int page, int size) {
        logger.info(BookInteractorLogConstants.GET_PUBLISHER_BOOKS_REQUEST, email, keyword, page, size);

        User publisher = userCommandRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        PageQuery query = new PageQuery(page, size);
        PageResult<Book> domainBook = bookQueryRepositoryPort.searchPublisherBooksById(publisher.getId(), keyword, query);

        logger.info(BookInteractorLogConstants.GET_PUBLISHER_BOOKS_FETCHED, domainBook.totalElements());
        return domainBook.map(GetBooksResult::fromDomain);
    }
}
