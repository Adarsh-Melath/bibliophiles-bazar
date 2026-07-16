package com.adarsh.backend.feature.category.application.port;

import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.category.domain.model.CategoryType;

public interface CategoryCommandPort {
    Category save(Category category);

    boolean existsByCategoryType(CategoryType type);
}
