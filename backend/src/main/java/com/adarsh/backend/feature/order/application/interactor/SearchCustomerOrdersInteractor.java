package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.order.application.dto.result.SearchCustomerOrdersResult;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderSearchCriteria;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.application.usecase.SearchCustomerOrdersUseCase;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.adarsh.backend.feature.order.application.interactor.constant.OrderInteractorLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SearchCustomerOrdersInteractor implements SearchCustomerOrdersUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SearchCustomerOrdersInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final OrderQueryRepositoryPort orderQueryRepositoryPort;


    @Override
    public PageResult<SearchCustomerOrdersResult> execute(String email, String keyword, OrderStatus orderStatus, LocalDateTime startDate, LocalDateTime endDate, int page, int size, OrderSortOption sortOption) {
        logger.info(OrderInteractorLogConstants.SEARCH_ORDERS_REQUEST, email, page, size);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.SEARCH_ORDERS_USER_FOUND, user.getId());
        PageQuery query = new PageQuery(page, size);
        OrderSearchCriteria criteria = new OrderSearchCriteria.Builder().customerId(user.getId()).keyword(keyword).orderStatus(orderStatus).startDate(startDate).endDate(endDate).sortOption(sortOption).build();
        PageResult<Order> domainPage = orderQueryRepositoryPort.search(query, criteria);

        PageResult<SearchCustomerOrdersResult> result = domainPage.map(SearchCustomerOrdersResult::fromDomain);
        logger.info(OrderInteractorLogConstants.SEARCH_ORDERS_SUCCESS, result.content().size(), user.getId());
        return result;
    }
}