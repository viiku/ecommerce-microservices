package com.vikku.ProductService.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId); // Matches the field name in OrderEntity

    ProductEntity findByProductIdOrTitle(String productId, String title);
}