package com.adarsh.backend.feature.book.infrastructure.persistence.jpaRepository;

import com.adarsh.backend.feature.book.infrastructure.persistence.entity.BookEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    BookEntity save(BookEntity bookEntity);

    boolean existsByIsbn(String isbn);

    Optional<BookEntity> findBySlug(String slug);

    @Query("""
                SELECT b
                FROM BookEntity b
                WHERE b.deleted = false
                  AND (
                      :keyword IS NULL
                      OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                      OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%'))
                      OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))
                  )
            """)
    Page<BookEntity> searchBooksAndDeletedFalse(@Param("keyword") String keyword, Pageable pageable);

    @NullMarked
    Page<BookEntity> findAll(Specification<BookEntity> specification, Pageable pageable);
}
