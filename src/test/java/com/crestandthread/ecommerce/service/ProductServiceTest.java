// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service;

import com.crestandthread.ecommerce.dto.ProductDTO;
import com.crestandthread.ecommerce.entity.Category;
import com.crestandthread.ecommerce.entity.Product;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.exception.ResourceNotFoundException;
import com.crestandthread.ecommerce.mapper.ProductMapper;
import com.crestandthread.ecommerce.repository.CategoryRepository;
import com.crestandthread.ecommerce.repository.ProductRepository;
import com.crestandthread.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Knitwear");
        category.setSlug("knitwear");

        product = new Product();
        product.setId(1L);
        product.setSku("CT-KW-001");
        product.setName("Cashmere Crew Sweater");
        product.setDescription("Luxuriously soft cashmere sweater");
        product.setPrice(new BigDecimal("189.00"));
        product.setCategory(category);
        product.setActive(true);
        product.setIsNew(true);
        product.setIsFeatured(true);

        productDTO = ProductDTO.builder()
                .id(1L)
                .sku("CT-KW-001")
                .name("Cashmere Crew Sweater")
                .description("Luxuriously soft cashmere sweater")
                .price(new BigDecimal("189.00"))
                .categoryId(1L)
                .categoryName("Knitwear")
                .active(true)
                .isNew(true)
                .isFeatured(true)
                .build();
    }

    @Nested
    @DisplayName("getAllProducts")
    class GetAllProducts {

        @Test
        @DisplayName("should return paginated products")
        void shouldReturnPaginatedProducts() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Product> productPage = new PageImpl<>(Arrays.asList(product));
            
            when(productRepository.findByActiveTrue(pageable)).thenReturn(productPage);
            when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

            Page<ProductDTO> result = productService.getAllProducts(pageable);

            assertThat(result.getContent()).hasSize(1);
            assertThat(result.getContent().get(0).getName()).isEqualTo("Cashmere Crew Sweater");
        }
    }

    @Nested
    @DisplayName("getProductById")
    class GetProductById {

        @Test
        @DisplayName("should return product when found")
        void shouldReturnProductWhenFound() {
            when(productRepository.findById(1L)).thenReturn(Optional.of(product));
            when(productMapper.toDTO(product)).thenReturn(productDTO);

            Optional<ProductDTO> result = productService.getProductById(1L);

            assertThat(result).isPresent();
            assertThat(result.get().getName()).isEqualTo("Cashmere Crew Sweater");
        }

        @Test
        @DisplayName("should return empty when not found")
        void shouldReturnEmptyWhenNotFound() {
            when(productRepository.findById(999L)).thenReturn(Optional.empty());

            Optional<ProductDTO> result = productService.getProductById(999L);

            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("createProduct")
    class CreateProduct {

        @Test
        @DisplayName("should create product successfully")
        void shouldCreateProductSuccessfully() {
            ProductDTO inputDTO = ProductDTO.builder()
                    .sku("CT-KW-002")
                    .name("New Product")
                    .price(new BigDecimal("99.00"))
                    .categoryId(1L)
                    .build();

            Product newProduct = new Product();
            newProduct.setSku("CT-KW-002");
            newProduct.setName("New Product");

            when(productRepository.existsBySku("CT-KW-002")).thenReturn(false);
            when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
            when(productMapper.toEntity(inputDTO)).thenReturn(newProduct);
            when(productRepository.save(any(Product.class))).thenReturn(product);
            when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

            ProductDTO result = productService.createProduct(inputDTO);

            verify(productRepository).save(any(Product.class));
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("should throw exception when SKU already exists")
        void shouldThrowExceptionWhenSkuExists() {
            ProductDTO inputDTO = ProductDTO.builder()
                    .sku("CT-KW-001")
                    .name("Duplicate Product")
                    .price(new BigDecimal("99.00"))
                    .categoryId(1L)
                    .build();

            when(productRepository.existsBySku("CT-KW-001")).thenReturn(true);

            assertThatThrownBy(() -> productService.createProduct(inputDTO))
                    .isInstanceOf(DuplicateResourceException.class)
                    .hasMessageContaining("already exists");
        }
    }

    @Nested
    @DisplayName("deleteProduct")
    class DeleteProduct {

        @Test
        @DisplayName("should soft delete product")
        void shouldSoftDeleteProduct() {
            when(productRepository.findById(1L)).thenReturn(Optional.of(product));
            when(productRepository.save(any(Product.class))).thenReturn(product);

            productService.deleteProduct(1L);

            assertThat(product.getActive()).isFalse();
            verify(productRepository).save(product);
        }
    }
}
// AI Generated Code by Deloitte + Cursor (END)