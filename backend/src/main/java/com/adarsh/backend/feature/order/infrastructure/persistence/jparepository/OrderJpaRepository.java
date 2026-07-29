package com.adarsh.backend.feature.order.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    Optional<OrderEntity> findByCustomerIdAndId(Long customerId, Long id);
}
