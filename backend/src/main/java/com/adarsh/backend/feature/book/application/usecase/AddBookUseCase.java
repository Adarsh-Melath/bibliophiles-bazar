package com.adarsh.backend.feature.book.application.usecase;

import com.adarsh.backend.feature.book.application.dto.command.AddBookCommand;
import com.adarsh.backend.feature.book.application.dto.result.AddBookResult;

public interface AddBookUseCase {
    AddBookResult execute(String email,AddBookCommand command);
}
