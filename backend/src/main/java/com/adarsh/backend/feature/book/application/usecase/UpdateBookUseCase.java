package com.adarsh.backend.feature.book.application.usecase;

import com.adarsh.backend.feature.book.application.dto.command.UpdateBookCommand;
import com.adarsh.backend.feature.book.application.dto.result.UpdateBookResult;

public interface UpdateBookUseCase {
    UpdateBookResult execute(String slug, UpdateBookCommand command);
}
