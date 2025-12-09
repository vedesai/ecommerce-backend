// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service.impl;

import com.crestandthread.ecommerce.dto.ProductDTO;
import com.crestandthread.ecommerce.entity.Category;
import com.crestandthread.ecommerce.entity.Product;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.exception.ResourceNotFoundException;
import com.crestandthread.ecommerce.mapper.ProductMapper;
import com.crestandthread.ecommerce.repository.CategoryRepository;
import com.crestandthread.ecommerce.repository.ProductRepository;
import com.crestandthread.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable)
                .map(productMapper::toDTO);
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    @Override
    public Optional<ProductDTO> getProductBySku(String sku) {
        return productRepository.findBySku(sku)
                .map(productMapper::toDTO);
    }

    @Override
    public List<ProductDTO> getNewArrivals() {
        return productRepository.findByActiveTrueAndIsNewTrueOrderByCreatedAtDesc()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getFeaturedProducts() {
        return productRepository.findByActiveTrueAndIsFeaturedTrueOrderByCreatedAtDesc()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getProductsByCategory(String categorySlug, Pageable pageable) {
        return productRepository.findByCategorySlug(categorySlug, pageable)
                .map(productMapper::toDTO);
    }

    @Override
    public List<ProductDTO> getOnSaleProducts() {
        return productRepository.findOnSaleProducts()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> searchProducts(String query, Pageable pageable) {
        return productRepository.searchByName(query, pageable)
                .map(productMapper::toDTO);
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productRepository.existsBySku(productDTO.getSku())) {
            throw new DuplicateResourceException("Product", "sku", productDTO.getSku());
        }

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDTO.getCategoryId()));

        Product product = productMapper.toEntity(productDTO);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        if (productDTO.getSku() != null && !productDTO.getSku().equals(product.getSku())) {
            if (productRepository.existsBySku(productDTO.getSku())) {
                throw new DuplicateResourceException("Product", "sku", productDTO.getSku());
            }
        }

        if (productDTO.getCategoryId() != null && !productDTO.getCategoryId().equals(product.getCategory().getId())) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDTO.getCategoryId()));
            product.setCategory(category);
        }

        productMapper.updateEntity(product, productDTO);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setActive(false);
        productRepository.save(product);
    }
}
// AI Generated Code by Deloitte + Cursor (END)