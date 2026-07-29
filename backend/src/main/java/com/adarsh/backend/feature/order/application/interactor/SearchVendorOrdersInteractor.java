package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.dto.result.SearchVendorOrdersResult;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderSearchCriteria;
import com.adarsh.backend.feature.order.application.usecase.SearchVendorOrdersUseCase;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderItemStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchVendorOrdersInteractor implements SearchVendorOrdersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SearchVendorOrdersInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final OrderQueryRepositoryPort orderQueryRepositoryPort;

    @Override
    public PageResult<SearchVendorOrdersResult> execute(String email, String keyword, OrderItemStatus itemStatus, PaymentStatus paymentStatus, LocalDateTime startDate, LocalDateTime endDate, int page, int size, OrderSortOption sortOption) {
        logger.info(OrderInteractorLogConstants.SEARCH_VENDOR_ORDERS_REQUEST, email, itemStatus);
        User vendor = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        List<Long> vendorBookIds = bookQueryRepositoryPort.findBookIdsByPublisherId(vendor.getId());

        PageQuery query = new PageQuery(page, size);
        OrderSearchCriteria criteria = new OrderSearchCriteria.Builder().allowedBookIds(vendorBookIds).keyword(keyword).itemStatus(itemStatus).paymentStatus(paymentStatus).startDate(startDate).endDate(endDate).sortOption(sortOption).build();

        PageResult<Order> domainPage = orderQueryRepositoryPort.search(query, criteria);

        PageResult<SearchVendorOrdersResult> result = domainPage.map(order -> SearchVendorOrdersResult.fromDomain(order, vendorBookIds));
        logger.info(OrderInteractorLogConstants.SEARCH_VENDOR_ORDERS_SUCCESS, result.content().size());
        return result;
    }
}
