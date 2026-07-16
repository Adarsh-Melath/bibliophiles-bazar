package com.adarsh.backend.feature.book.application.interactor;

import com.adarsh.backend.feature.book.application.dto.command.AddBookCommand;
import com.adarsh.backend.feature.book.application.dto.result.AddBookResult;
import com.adarsh.backend.feature.book.application.interactor.constant.BookInteractorLogConstants;
import com.adarsh.backend.feature.book.application.port.BookCommandRepositoryPort;
import com.adarsh.backend.feature.book.application.usecase.AddBookUseCase;
import com.adarsh.backend.feature.book.domain.exception.BookAlreadyExistsException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.book.domain.model.BookImage;
import com.adarsh.backend.feature.category.domain.SlugGenerator;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddBookInteractor implements AddBookUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddBookInteractor.class);

    private final BookCommandRepositoryPort bookCommandRepositoryPort;
    private final UserCommandRepository userCommandRepository;

    @Override
    public AddBookResult execute(String email, AddBookCommand command) {
        logger.info(BookInteractorLogConstants.ADD_BOOK_REQUEST, email, command.isbn());

        logger.info(BookInteractorLogConstants.ADD_BOOK_ISBN_CHECK, command.isbn());

        if (bookCommandRepositoryPort.existsByIsbn(command.isbn())) {
            throw new BookAlreadyExistsException(BookExceptionMessageConstants.BOOK_ALREADY_EXISTS);
        }

        User publisher = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        String slug = SlugGenerator.slugGenerator(command.title());

        logger.info(BookInteractorLogConstants.ADD_BOOK_SLUG_GENERATED, slug, command.title());

        List<BookImage> images = command.images().stream().map(imageUrl -> new BookImage.Builder().url(imageUrl).build()).toList();

        Book newBook = new Book.Builder().publisherId(publisher.getId()).title(command.title()).slug(slug).author(command.author()).description(command.description()).isbn(command.isbn()).category(command.categoryType()).price(command.price()).stock(command.stock()).language(command.language()).pages(command.pages()).images(images).build();

        Book savedBook = bookCommandRepositoryPort.save(newBook);

        logger.info(BookInteractorLogConstants.ADD_BOOK_SAVED, savedBook.getId(), savedBook.getIsbn());

        return AddBookResult.fromDomain(savedBook);
    }
}
