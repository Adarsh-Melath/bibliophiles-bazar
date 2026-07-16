package com.adarsh.backend.feature.book.application.usecase;

import com.adarsh.backend.feature.book.application.dto.result.GetBooksResult;
import com.adarsh.backend.shared.domain.pagination.PageResult;

public interface GetBooksUseCase {
    PageResult<GetBooksResult> execute( String keyword, int page, int size);
}
