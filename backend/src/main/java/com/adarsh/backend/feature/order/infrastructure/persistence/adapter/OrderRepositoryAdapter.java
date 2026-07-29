package com.adarsh.backend.feature.order.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.order.application.port.OrderCommandRepositoryPort;
import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.port.SearchCustomerOrdersCriteria;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import com.adarsh.backend.feature.order.infrastructure.persistence.jparepository.OrderJpaRepository;
import com.adarsh.backend.feature.order.infrastructure.persistence.mapper.OrderPersistenceMapper;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
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
    public PageResult<Order> search(PageQuery pageQuery, SearchCustomerOrdersCriteria criteria) {
        Pageable pageable = PageRequest.of(pageQuery.page(), pageQuery.size(), Sort.by("createdAt").descending());
        Page<OrderEntity> springPage = orderJpaRepository.searchOrders(criteria.keyword(), pageable);
        List<Order> domainOrders = springPage.getContent().stream().map(orderPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainOrders, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    @Override
    public Optional<Order> findByUserIdAndOrderId(Long userId, Long orderId) {
        return orderJpaRepository.findByCustomerIdAndId(userId, orderId).map(orderPersistenceMapper::toDomain);
    }
}
