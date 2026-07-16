package com.adarsh.backend.feature.category.infrastructure.jparepository;

import com.adarsh.backend.feature.category.domain.model.CategoryType;
import com.adarsh.backend.feature.category.infrastructure.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity save(CategoryEntity entity);

    boolean existsByType(CategoryType type);

    Optional<CategoryEntity> findBySlug(String slug);

    @Query("SELECT c FROM CategoryEntity c WHERE " + "(c.deleted IS NULL OR c.deleted = false) " + "AND (:keyword IS NULL OR LOWER(c.type) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.slug) " + "LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    Page<CategoryEntity> searchCategories(@Param("keyword") String keyword, Pageable pageable);
}
