package com.adarsh.backend.feature.book.application.port;

import com.adarsh.backend.feature.book.domain.model.Book;

public interface BookCommandRepositoryPort {
    Book save(Book book);
    boolean existsByIsbn(String isbn);
}
