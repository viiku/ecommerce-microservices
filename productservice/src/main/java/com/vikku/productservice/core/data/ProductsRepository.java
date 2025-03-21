package com.vikku.productservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId); // Matches the field name in OrderEntity
    ProductEntity findByProductIdOrTitle(String productId, String title);
}