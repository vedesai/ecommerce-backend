// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.config;

import com.crestandthread.ecommerce.entity.Category;
import com.crestandthread.ecommerce.entity.Product;
import com.crestandthread.ecommerce.repository.CategoryRepository;
import com.crestandthread.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!prod")
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            log.info("Database already seeded, skipping initialization");
            return;
        }

        log.info("Initializing sample data...");
        initializeCategories();
        initializeProducts();
        log.info("Sample data initialized successfully");
    }

    private void initializeCategories() {
        Category womens = createCategory("Women's Collection", "women", 
                "Elegant and sophisticated women's apparel",
                "https://images.unsplash.com/photo-1487412720507-e7ab37603c6f?w=500&h=600&fit=crop",
                1);

        Category mens = createCategory("Men's Collection", "men",
                "Classic and contemporary men's fashion",
                "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=500&h=600&fit=crop",
                2);

        Category accessories = createCategory("Accessories", "accessories",
                "Premium accessories to complete your look",
                "https://images.unsplash.com/photo-1523779105320-d1cd346ff52b?w=500&h=600&fit=crop",
                3);

        Category knitwear = createCategory("Knitwear", "knitwear",
                "Luxurious knitwear for any season",
                "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=500&h=600&fit=crop",
                4);

        Category outerwear = createCategory("Outerwear", "outerwear",
                "Premium coats and jackets",
                "https://images.unsplash.com/photo-1539533018447-63fcce2678e3?w=500&h=600&fit=crop",
                5);

        Category essentials = createCategory("Essentials", "essentials",
                "Everyday wardrobe essentials",
                "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500&h=600&fit=crop",
                6);

        Category denim = createCategory("Denim", "denim",
                "Premium denim collection",
                "https://images.unsplash.com/photo-1542272604-787c3835535d?w=500&h=600&fit=crop",
                7);

        categoryRepository.save(womens);
        categoryRepository.save(mens);
        categoryRepository.save(accessories);
        categoryRepository.save(knitwear);
        categoryRepository.save(outerwear);
        categoryRepository.save(essentials);
        categoryRepository.save(denim);
    }

    private Category createCategory(String name, String slug, String description, String image, int order) {
        Category category = new Category();
        category.setName(name);
        category.setSlug(slug);
        category.setDescription(description);
        category.setImageUrl(image);
        category.setDisplayOrder(order);
        category.setActive(true);
        return category;
    }

    private void initializeProducts() {
        Category knitwear = categoryRepository.findBySlug("knitwear").orElseThrow();
        Category outerwear = categoryRepository.findBySlug("outerwear").orElseThrow();
        Category essentials = categoryRepository.findBySlug("essentials").orElseThrow();
        Category denim = categoryRepository.findBySlug("denim").orElseThrow();

        createProduct("CT-KW-001", "Cashmere Crew Sweater", knitwear,
                "Luxuriously soft cashmere sweater with a classic crew neck design.",
                new BigDecimal("189.00"), null,
                "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=400&h=500&fit=crop",
                true, true);

        createProduct("CT-OW-001", "Tailored Wool Coat", outerwear,
                "Impeccably tailored wool coat with a modern silhouette.",
                new BigDecimal("349.00"), null,
                "https://images.unsplash.com/photo-1539533018447-63fcce2678e3?w=400&h=500&fit=crop",
                true, true);

        createProduct("CT-ES-001", "Classic White Tee", essentials,
                "The perfect white t-shirt. Premium cotton, relaxed fit.",
                new BigDecimal("45.00"), null,
                "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400&h=500&fit=crop",
                false, false);

        createProduct("CT-DN-001", "Selvedge Denim Jeans", denim,
                "Premium selvedge denim crafted in Japan.",
                new BigDecimal("168.00"), null,
                "https://images.unsplash.com/photo-1542272604-787c3835535d?w=400&h=500&fit=crop",
                false, true);

        createProduct("CT-KW-002", "Merino Wool Cardigan", knitwear,
                "Fine merino wool cardigan with mother of pearl buttons.",
                new BigDecimal("145.00"), new BigDecimal("115.00"),
                "https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?w=400&h=500&fit=crop",
                false, false);

        createProduct("CT-OW-002", "Leather Bomber Jacket", outerwear,
                "Classic leather bomber jacket with ribbed cuffs.",
                new BigDecimal("495.00"), null,
                "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400&h=500&fit=crop",
                true, true);

        createProduct("CT-ES-002", "Oxford Button-Down Shirt", essentials,
                "Classic oxford cloth button-down shirt.",
                new BigDecimal("85.00"), null,
                "https://images.unsplash.com/photo-1598033129183-c4f50c736f10?w=400&h=500&fit=crop",
                false, false);

        createProduct("CT-DN-002", "Slim Fit Chinos", denim,
                "Perfectly fitted chinos in premium stretch cotton.",
                new BigDecimal("98.00"), new BigDecimal("78.00"),
                "https://images.unsplash.com/photo-1473966968600-fa801b869a1a?w=400&h=500&fit=crop",
                false, false);
    }

    private void createProduct(String sku, String name, Category category, String description,
                               BigDecimal price, BigDecimal salePrice, String image,
                               boolean isNew, boolean isFeatured) {
        Product product = new Product();
        product.setSku(sku);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setSalePrice(salePrice);
        product.setImageUrl(image);
        product.setIsNew(isNew);
        product.setIsFeatured(isFeatured);
        product.setActive(true);
        productRepository.save(product);
    }
}
// AI Generated Code by Deloitte + Cursor (END)