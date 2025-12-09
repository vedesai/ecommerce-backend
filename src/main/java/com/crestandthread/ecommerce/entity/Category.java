// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a product category.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String slug;

    @Column(length = 500)
    private String description;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    /**
     * Get the count of active products in this category.
     */
    @Transient
    public int getProductCount() {
        return (int) products.stream().filter(Product::getActive).count();
    }
}
// AI Generated Code by Deloitte + Cursor (END)