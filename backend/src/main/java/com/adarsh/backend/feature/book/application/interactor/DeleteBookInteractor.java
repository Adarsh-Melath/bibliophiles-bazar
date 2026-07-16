package com.adarsh.backend.feature.book.application.interactor;

import com.adarsh.backend.feature.book.application.interactor.constant.BookInteractorLogConstants;
import com.adarsh.backend.feature.book.application.port.BookCommandRepositoryPort;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.usecase.DeleteBookUseCase;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookInteractor implements DeleteBookUseCase {
    private static final Logger logger = LoggerFactory.getLogger(DeleteBookInteractor.class);

    private final BookCommandRepositoryPort bookCommandRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    public void execute(String slug) {
        logger.info(BookInteractorLogConstants.DELETE_BOOK_REQUEST, slug);

        Book book = bookQueryRepositoryPort.findBySlug(slug).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));

        logger.debug(BookInteractorLogConstants.DELETE_BOOK_FOUND, slug);
        book.softDelete();
        bookCommandRepositoryPort.save(book);

        logger.info(BookInteractorLogConstants.DELETE_BOOK_DELETED, slug);
    }
}
