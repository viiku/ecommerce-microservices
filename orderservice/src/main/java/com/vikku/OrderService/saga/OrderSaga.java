package com.vikku.OrderService.saga;

import com.vikku.OrderService.core.events.OrderCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    // since saga is serialized
    // we mark injected components with transient so that it does not get serialized

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {

    }
}
