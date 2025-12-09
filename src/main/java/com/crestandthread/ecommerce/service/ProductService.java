// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service;

import com.crestandthread.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Product operations.
 */
public interface ProductService {

    Page<ProductDTO> getAllProducts(Pageable pageable);

    Optional<ProductDTO> getProductById(Long id);

    Optional<ProductDTO> getProductBySku(String sku);

    List<ProductDTO> getNewArrivals();

    List<ProductDTO> getFeaturedProducts();

    Page<ProductDTO> getProductsByCategory(String categorySlug, Pageable pageable);

    List<ProductDTO> getOnSaleProducts();

    Page<ProductDTO> searchProducts(String query, Pageable pageable);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
// AI Generated Code by Deloitte + Cursor (END)