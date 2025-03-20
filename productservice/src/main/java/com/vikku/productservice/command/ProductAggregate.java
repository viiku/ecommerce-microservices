package com.vikku.productservice.command;

import com.vikku.productservice.core.events.ProductCreatedEvent;
import com.vikku.core.command.ReserveProductCommand;
import com.vikku.core.command.events.ProductReservedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
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
    public ProductAggregate(CreateProductCommand createProductCommand) throws Exception {
        //        Basic Validation or command validation
        if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price can not be less than zero.");
        }

//        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
//            throw new IllegalArgumentException("Product title can not be empty.");
//        }

        if (createProductCommand.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity can not be less than zero.");
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

//        When apply method is called, axon framework does not immediately
//        persist this event to event store instead only stages this event for execution
        AggregateLifecycle.apply(productCreatedEvent);

//        if error is thrown then transaction will be rolled back
//        and none of the events will be processed
//        if(true) throw new Exception("An error took place in CreateProductCommand command handler method");
    }

    @CommandHandler
    public void handle(ReserveProductCommand reserveProductCommand) {

        if(quantity < reserveProductCommand.getQuantity()) {
            throw  new IllegalArgumentException("Insufficient number of items in stock");
        }

        ProductReservedEvent productReservedEvent = new ProductReservedEvent(
                reserveProductCommand.getProductId(),
                reserveProductCommand.getQuantity(),
                reserveProductCommand.getOrderId(),
                reserveProductCommand.getUserId()
        );

        AggregateLifecycle.apply(productReservedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent productReservedEvent) {
        this.quantity -= productReservedEvent.getQuantity();
    }
}
