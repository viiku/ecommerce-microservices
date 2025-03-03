package com.vikku.ProductService.query;

import com.vikku.ProductService.core.data.ProductEntity;
import com.vikku.ProductService.core.data.ProductsRepository;
import com.vikku.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

//    constructor based dependency injection
    ProductEventsHandler(ProductsRepository productRepository) {
        this.productsRepository = productRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handler(Exception exception) throws Exception {
//        Log error message
        throw exception;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handler(IllegalArgumentException exception) {
//        Log error message
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

        if(true) throw new Exception("Force exception in event handler class");
    }
}
