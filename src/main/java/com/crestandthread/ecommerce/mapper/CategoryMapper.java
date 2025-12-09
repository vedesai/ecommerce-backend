// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.mapper;

import com.crestandthread.ecommerce.dto.CategoryDTO;
import com.crestandthread.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .image(category.getImageUrl())
                .displayOrder(category.getDisplayOrder())
                .active(category.getActive())
                .productCount(category.getProducts() != null ? category.getProducts().size() : 0)
                .build();
    }

    public Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setName(dto.getName());
        category.setSlug(dto.getSlug());
        category.setDescription(dto.getDescription());
        category.setImageUrl(dto.getImage());
        category.setDisplayOrder(dto.getDisplayOrder());
        category.setActive(dto.getActive() != null ? dto.getActive() : true);
        
        return category;
    }

    public void updateEntity(Category category, CategoryDTO dto) {
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getSlug() != null) {
            category.setSlug(dto.getSlug());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
        if (dto.getImage() != null) {
            category.setImageUrl(dto.getImage());
        }
        if (dto.getDisplayOrder() != null) {
            category.setDisplayOrder(dto.getDisplayOrder());
        }
        if (dto.getActive() != null) {
            category.setActive(dto.getActive());
        }
    }
}
// AI Generated Code by Deloitte + Cursor (END)