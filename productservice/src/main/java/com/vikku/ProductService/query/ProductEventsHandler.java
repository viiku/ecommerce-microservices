package com.vikku.ProductService.query;

import com.vikku.ProductService.core.data.ProductEntity;
import com.vikku.ProductService.core.data.ProductsRepository;
import com.vikku.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

//    constructor based dependency injection
    ProductEventsHandler(ProductsRepository productRepository) {
        this.productsRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
//        Accept what event needs to handle

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        try {
            productsRepository.save(productEntity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
