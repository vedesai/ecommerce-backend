// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.repository;

import com.crestandthread.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Product entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    List<Product> findByActiveTrue();

    Page<Product> findByActiveTrue(Pageable pageable);

    List<Product> findByActiveTrueAndIsNewTrueOrderByCreatedAtDesc();

    List<Product> findByActiveTrueAndIsFeaturedTrueOrderByCreatedAtDesc();

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.slug = :categorySlug AND p.active = true")
    Page<Product> findByCategorySlug(@Param("categorySlug") String categorySlug, Pageable pageable);

    List<Product> findByCategoryIdAndActiveTrue(Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.salePrice IS NOT NULL AND p.salePrice < p.price ORDER BY p.createdAt DESC")
    List<Product> findOnSaleProducts();

    @Query("SELECT p FROM Product p WHERE p.active = true AND LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Product> searchByName(@Param("query") String query, Pageable pageable);

    boolean existsBySku(String sku);
}
// AI Generated Code by Deloitte + Cursor (END)