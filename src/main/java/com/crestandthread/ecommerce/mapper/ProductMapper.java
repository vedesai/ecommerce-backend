// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.mapper;

import com.crestandthread.ecommerce.dto.ProductDTO;
import com.crestandthread.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO.ProductDTOBuilder builder = ProductDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .image(product.getImageUrl())
                .isNew(product.getIsNew())
                .isFeatured(product.getIsFeatured())
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt());

        if (product.getCategory() != null) {
            builder.categoryId(product.getCategory().getId())
                    .categoryName(product.getCategory().getName())
                    .categorySlug(product.getCategory().getSlug());
        }

        return builder.build();
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setSalePrice(dto.getSalePrice());
        product.setImageUrl(dto.getImage());
        product.setIsNew(dto.getIsNew() != null ? dto.getIsNew() : false);
        product.setIsFeatured(dto.getIsFeatured() != null ? dto.getIsFeatured() : false);
        product.setActive(dto.getActive() != null ? dto.getActive() : true);
        
        return product;
    }

    public void updateEntity(Product product, ProductDTO dto) {
        if (dto.getSku() != null) {
            product.setSku(dto.getSku());
        }
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getSalePrice() != null) {
            product.setSalePrice(dto.getSalePrice());
        }
        if (dto.getImage() != null) {
            product.setImageUrl(dto.getImage());
        }
        if (dto.getIsNew() != null) {
            product.setIsNew(dto.getIsNew());
        }
        if (dto.getIsFeatured() != null) {
            product.setIsFeatured(dto.getIsFeatured());
        }
        if (dto.getActive() != null) {
            product.setActive(dto.getActive());
        }
    }
}
// AI Generated Code by Deloitte + Cursor (END)