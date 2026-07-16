package com.adarsh.backend.feature.book.application.port;

import com.adarsh.backend.feature.book.domain.model.SortOption;
import com.adarsh.backend.feature.category.domain.model.CategoryType;

public record PublishedBookSearchCriteria(String keyword, SortOption sortOption,
                                          CategoryType type,Double minPrice,Double maxPrice,
                                          int page, int size) {
}
