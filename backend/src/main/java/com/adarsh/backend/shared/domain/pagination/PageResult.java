package com.adarsh.backend.shared.domain.pagination;

import java.util.List;
import java.util.function.Function;

public record PageResult<T>(List<T> content, int page, int size, int totalElements,
                            int totalPages) {

    public <R> PageResult<R> map(Function<T, R> mapper) {
        List<R> mappedContent = content.stream().map(mapper).toList();
        return new PageResult<>(mappedContent, page, size, totalElements, totalPages);
    }
}
