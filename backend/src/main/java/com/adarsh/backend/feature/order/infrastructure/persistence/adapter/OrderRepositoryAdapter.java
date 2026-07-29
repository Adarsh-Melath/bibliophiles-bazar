package com.adarsh.backend.feature.order.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.order.application.port.OrderCommandRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderSearchCriteria;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.infrastructure.persistence.constant.OrderPersistenceConstants;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import com.adarsh.backend.feature.order.infrastructure.persistence.jparepository.OrderJpaRepository;
import com.adarsh.backend.feature.order.infrastructure.persistence.mapper.OrderPersistenceMapper;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import com.adarsh.backend.feature.order.infrastructure.persistence.specification.OrderSpecification;
import org.springframework.data.jpa.domain.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderCommandRepositoryPort, OrderQueryRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order save(Order order) {
        OrderEntity entity = orderPersistenceMapper.toEntityWithItems(order);
        OrderEntity savedEntity = orderJpaRepository.save(entity);
        return orderPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public PageResult<Order> search(PageQuery pageQuery, OrderSearchCriteria criteria) {
        Sort sort = toSpringSort(criteria.getSortOption());
        Pageable pageable = PageRequest.of(pageQuery.page(), pageQuery.size(), sort);
        Specification<OrderEntity> spec = OrderSpecification.build(criteria);
        Page<OrderEntity> springPage = orderJpaRepository.findAll(spec, pageable);
        List<Order> domainOrders = springPage.getContent().stream().map(orderPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainOrders, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    private Sort toSpringSort(OrderSortOption sortOption) {
        if (sortOption == null) {
            return Sort.by(OrderPersistenceConstants.SORT_FIELD_CREATED_AT).descending();
        }
        return switch (sortOption) {
            case NEWEST -> Sort.by(OrderPersistenceConstants.SORT_FIELD_CREATED_AT).descending();
            case OLDEST -> Sort.by(OrderPersistenceConstants.SORT_FIELD_CREATED_AT).ascending();
            case GRAND_TOTAL_ASC ->
                    Sort.by(OrderPersistenceConstants.SORT_FIELD_GRAND_TOTAL).ascending();
            case GRAND_TOTAL_DESC ->
                    Sort.by(OrderPersistenceConstants.SORT_FIELD_GRAND_TOTAL).descending();
        };
    }

    @Override
    public Optional<Order> findByUserIdAndOrderId(Long userId, Long orderId) {
        return orderJpaRepository.findByCustomerIdAndId(userId, orderId).map(orderPersistenceMapper::toDomain);
    }
}
