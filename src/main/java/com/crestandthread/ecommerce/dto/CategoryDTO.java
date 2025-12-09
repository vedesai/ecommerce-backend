// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Category.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name must be less than 100 characters")
    private String name;

    private String slug;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    private String image;

    private Integer displayOrder;

    private Boolean active;

    private Integer productCount;
}
// AI Generated Code by Deloitte + Cursor (END)