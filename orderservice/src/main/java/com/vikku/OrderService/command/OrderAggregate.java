package com.vikku.orderservice.command;

import com.vikku.orderservice.core.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    public String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    public OrderAggregate() {

    }

//    Handler for creating order
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
//        Basic Validation

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);

//        Emitting event using aggregate lifecycle
        AggregateLifecycle.apply(orderCreatedEvent);
    }
}
