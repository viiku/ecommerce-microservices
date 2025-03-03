package com.vikku.OrderService.saga;

import com.vikku.OrderService.core.events.OrderCreatedEvent;
import com.vikku.core.command.ReserveProductCommand;
import com.vikku.core.command.events.ProductReservedEvent;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;

@Saga
public class OrderSaga {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;
    // since saga is serialized
    // we mark injected components with transient so that it does not get serialized

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductCommand reserveProductCommand = new ReserveProductCommand(
                orderCreatedEvent.getProductId(),
                orderCreatedEvent.getQuantity(),
                orderCreatedEvent.getOrderId(),
                orderCreatedEvent.getUserId()
        );

        LOGGER.info("OrderCreatedEvent handled for orderId: "+ reserveProductCommand.getOrderId() +
                "and productId: "+ reserveProductCommand.getProductId());

        commandGateway.send(reserveProductCommand, new CommandCallback<ReserveProductCommand, Object>() {

            @Override
            public void onResult(@Nonnull CommandMessage<? extends ReserveProductCommand> commandMessage, @Nonnull CommandResultMessage<?> commandResultMessage) {
                if(commandResultMessage.isExceptional()) {
//                    start a compensating transaction

                }
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
//        process user payments
        LOGGER.info("ProductReservedEvent is called for productId: "+ productReservedEvent.getProductId() +
                " and orderId: "+ productReservedEvent.getOrderId());
    }
}
