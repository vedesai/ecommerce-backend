// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entity representing a product in the catalog.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "sale_price", precision = 10, scale = 2)
    private BigDecimal salePrice;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "is_new", nullable = false)
    @Builder.Default
    private Boolean isNew = false;

    @Column(name = "is_featured", nullable = false)
    @Builder.Default
    private Boolean isFeatured = false;

    @Column(nullable = false)
    @Builder.Default
    private Integer stock = 0;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    /**
     * Check if product is on sale.
     */
    @Transient
    public boolean isOnSale() {
        return salePrice != null && salePrice.compareTo(price) < 0;
    }

    /**
     * Get the effective price (sale price if on sale, otherwise regular price).
     */
    @Transient
    public BigDecimal getEffectivePrice() {
        return isOnSale() ? salePrice : price;
    }
}
// AI Generated Code by Deloitte + Cursor (END)