package com.adarsh.backend.feature.category.application.interactor;

import com.adarsh.backend.feature.category.application.dto.result.GetCategoriesResult;
import com.adarsh.backend.feature.category.application.interactor.constant.CategoryInteractorLogConstants;
import com.adarsh.backend.feature.category.application.port.CategoryQueryPort;
import com.adarsh.backend.feature.category.application.port.CategorySearchCriteria;
import com.adarsh.backend.feature.category.application.usecase.GetCategoriesUseCase;
import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoriesInteractor implements GetCategoriesUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GetCategoriesInteractor.class);
    private final CategoryQueryPort categoryQueryPort;

    @Override
    public PageResult<GetCategoriesResult> execute(String keyword, int page, int size) {
        logger.info(CategoryInteractorLogConstants.GET_CATEGORIES_REQUEST, keyword, page, size);
        PageQuery query = new PageQuery(page, size);
        CategorySearchCriteria criteria = new CategorySearchCriteria(keyword);

        PageResult<Category> domainPage = categoryQueryPort.search(criteria, query);
        logger.info(CategoryInteractorLogConstants.GET_CATEGORIES_FETCHED);
        return domainPage.map(GetCategoriesResult::fromDomain);
    }
}
