package com.vikku.ProductService.command;

import com.vikku.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    
    public ProductAggregate() {}
    
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        //        Basic Validation
//        if(createProductCommand.getProductId() == null || createProductCommand.getProductId().isBlank()) {
//            throw new IllegalArgumentException("Product ID can not be empty.");
//        }
//
//        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
//            throw new IllegalArgumentException("Address Id can not be empty.");
//        }
//
//        if (createProductCommand.getQuantity() <= 0) {
//            throw new IllegalArgumentException("Quantity can not be less than one.");
//        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }
}
