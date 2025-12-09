// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service.impl;

import com.crestandthread.ecommerce.dto.CategoryDTO;
import com.crestandthread.ecommerce.entity.Category;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.exception.ResourceNotFoundException;
import com.crestandthread.ecommerce.mapper.CategoryMapper;
import com.crestandthread.ecommerce.repository.CategoryRepository;
import com.crestandthread.ecommerce.service.CategoryService;
import com.crestandthread.ecommerce.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findByActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO);
    }

    @Override
    public Optional<CategoryDTO> getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .map(categoryMapper::toDTO);
    }

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        String slug = categoryDTO.getSlug();
        if (slug == null || slug.isBlank()) {
            slug = SlugUtil.generateSlug(categoryDTO.getName());
        }

        if (categoryRepository.existsBySlug(slug)) {
            throw new DuplicateResourceException("Category", "slug", slug);
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setSlug(slug);
        
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        if (categoryDTO.getSlug() != null && !categoryDTO.getSlug().equals(category.getSlug())) {
            if (categoryRepository.existsBySlug(categoryDTO.getSlug())) {
                throw new DuplicateResourceException("Category", "slug", categoryDTO.getSlug());
            }
        }

        categoryMapper.updateEntity(category, categoryDTO);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> getAllCategoriesWithProductCount() {
        return categoryRepository.findAllWithProducts()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
// AI Generated Code by Deloitte + Cursor (END)