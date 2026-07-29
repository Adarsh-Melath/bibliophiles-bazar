package com.adarsh.backend.feature.order.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByCustomerIdAndId(Long customerId, Long id);

    @Query("SELECT o FROM OrderEntity o WHERE :keyword IS NULL OR o.orderNumber LIKE %:keyword%")
    Page<OrderEntity> searchOrders(@Param("keyword") String keyword, Pageable pageable);
}
