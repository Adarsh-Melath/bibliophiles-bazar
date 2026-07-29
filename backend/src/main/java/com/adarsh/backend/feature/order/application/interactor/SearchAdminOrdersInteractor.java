package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.order.application.dto.result.SearchAdminOrdersResult;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderSearchCriteria;
import com.adarsh.backend.feature.order.application.usecase.SearchAdminOrdersUseCase;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.PaymentMethod;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
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
public class SearchAdminOrdersInteractor implements SearchAdminOrdersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SearchAdminOrdersInteractor.class);
    private final OrderQueryRepositoryPort orderQueryRepositoryPort;

    @Override
    public PageResult<SearchAdminOrdersResult> execute(String keyword, OrderStatus orderStatus, PaymentStatus paymentStatus, PaymentMethod paymentMethod, Long customerId, LocalDateTime startDate, LocalDateTime endDate, Double minGrandTotal, Double maxGrandTotal, int page, int size, OrderSortOption sortOption) {
        logger.info(OrderInteractorLogConstants.SEARCH_ADMIN_ORDERS_REQUEST, keyword, orderStatus, customerId);
        PageQuery query = new PageQuery(page, size);
        OrderSearchCriteria criteria = new OrderSearchCriteria.Builder().customerId(customerId).keyword(keyword).orderStatus(orderStatus).paymentStatus(paymentStatus).paymentMethod(paymentMethod).startDate(startDate).endDate(endDate).minGrandTotal(minGrandTotal).maxGrandTotal(maxGrandTotal).sortOption(sortOption).build();
        PageResult<Order> domainPage = orderQueryRepositoryPort.search(query, criteria);

        PageResult<SearchAdminOrdersResult> result = domainPage.map(SearchAdminOrdersResult::fromDomain);
        logger.info(OrderInteractorLogConstants.SEARCH_ADMIN_ORDERS_SUCCESS, result.content().size());
        return result;
    }
}
