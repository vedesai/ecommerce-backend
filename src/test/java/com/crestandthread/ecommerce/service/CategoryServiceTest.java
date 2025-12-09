// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service;

import com.crestandthread.ecommerce.dto.CategoryDTO;
import com.crestandthread.ecommerce.entity.Category;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.exception.ResourceNotFoundException;
import com.crestandthread.ecommerce.mapper.CategoryMapper;
import com.crestandthread.ecommerce.repository.CategoryRepository;
import com.crestandthread.ecommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Women's Collection");
        category.setSlug("women");
        category.setDescription("Elegant women's apparel");
        category.setActive(true);

        categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Women's Collection")
                .slug("women")
                .description("Elegant women's apparel")
                .active(true)
                .build();
    }

    @Nested
    @DisplayName("getAllCategories")
    class GetAllCategories {

        @Test
        @DisplayName("should return all active categories")
        void shouldReturnAllActiveCategories() {
            List<Category> categories = Arrays.asList(category);
            when(categoryRepository.findByActiveTrueOrderByDisplayOrderAsc()).thenReturn(categories);
            when(categoryMapper.toDTO(any(Category.class))).thenReturn(categoryDTO);

            List<CategoryDTO> result = categoryService.getAllCategories();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getName()).isEqualTo("Women's Collection");
        }
    }

    @Nested
    @DisplayName("createCategory")
    class CreateCategory {

        @Test
        @DisplayName("should create category successfully")
        void shouldCreateCategorySuccessfully() {
            CategoryDTO inputDTO = CategoryDTO.builder()
                    .name("Men's Collection")
                    .build();

            Category newCategory = new Category();
            newCategory.setName("Men's Collection");

            Category savedCategory = new Category();
            savedCategory.setId(2L);
            savedCategory.setName("Men's Collection");
            savedCategory.setSlug("mens-collection");

            CategoryDTO savedDTO = CategoryDTO.builder()
                    .id(2L)
                    .name("Men's Collection")
                    .slug("mens-collection")
                    .build();

            when(categoryRepository.existsBySlug(anyString())).thenReturn(false);
            when(categoryMapper.toEntity(inputDTO)).thenReturn(newCategory);
            when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);
            when(categoryMapper.toDTO(savedCategory)).thenReturn(savedDTO);

            CategoryDTO result = categoryService.createCategory(inputDTO);

            assertThat(result.getId()).isEqualTo(2L);
            verify(categoryRepository).save(any(Category.class));
        }

        @Test
        @DisplayName("should throw exception when slug already exists")
        void shouldThrowExceptionWhenSlugExists() {
            CategoryDTO inputDTO = CategoryDTO.builder()
                    .name("Women's Collection")
                    .slug("women")
                    .build();

            when(categoryRepository.existsBySlug("women")).thenReturn(true);

            assertThatThrownBy(() -> categoryService.createCategory(inputDTO))
                    .isInstanceOf(DuplicateResourceException.class)
                    .hasMessageContaining("already exists");
        }
    }

    @Nested
    @DisplayName("deleteCategory")
    class DeleteCategory {

        @Test
        @DisplayName("should soft delete category")
        void shouldSoftDeleteCategory() {
            when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
            when(categoryRepository.save(any(Category.class))).thenReturn(category);

            categoryService.deleteCategory(1L);

            assertThat(category.getActive()).isFalse();
            verify(categoryRepository).save(category);
        }
    }
}
// AI Generated Code by Deloitte + Cursor (END)