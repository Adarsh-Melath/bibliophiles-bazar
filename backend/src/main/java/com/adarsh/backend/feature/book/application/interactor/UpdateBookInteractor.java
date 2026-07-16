package com.adarsh.backend.feature.book.application.interactor;

import com.adarsh.backend.feature.book.application.dto.command.UpdateBookCommand;
import com.adarsh.backend.feature.book.application.dto.result.UpdateBookResult;
import com.adarsh.backend.feature.book.application.interactor.constant.BookInteractorLogConstants;
import com.adarsh.backend.feature.book.application.port.BookCommandRepositoryPort;
import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.application.usecase.UpdateBookUseCase;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBookInteractor implements UpdateBookUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UpdateBookInteractor.class);

    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final BookCommandRepositoryPort bookCommandRepositoryPort;

    @Override
    public UpdateBookResult execute(String slug, UpdateBookCommand command) {
        logger.info(BookInteractorLogConstants.UPDATE_BOOK_REQUEST, slug);

        Book book = bookQueryRepositoryPort.findBySlug(slug).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));

        logger.debug(BookInteractorLogConstants.UPDATE_BOOK_FOUND, slug);
        book.updateBook(command.title(), command.author(), command.description(), command.categoryType(), command.price(), command.stock(), command.language(), command.pages());
        bookCommandRepositoryPort.save(book);

        logger.info(BookInteractorLogConstants.UPDATE_BOOK_SAVED, slug);
        return UpdateBookResult.fromDomain(book);
    }
}
