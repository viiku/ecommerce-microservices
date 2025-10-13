package com.vikku.orderservice.saga;

import com.vikku.orderservice.core.events.OrderCreatedEvent;
import com.vikku.core.command.ProcessPaymentCommand;
import com.vikku.core.command.ReserveProductCommand;
import com.vikku.core.command.events.ProductReservedEvent;
import com.vikku.core.model.User;
import com.vikku.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import java.util.UUID;

@Saga
public class OrderSaga {

    @Autowired
    private transient QueryGateway queryGateway;

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

        FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery = new FetchUserPaymentDetailsQuery(
                productReservedEvent.getUserId()
        );

        User userPaymentDetails = null;
        try {
            userPaymentDetails = (User) queryGateway.query(fetchUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
//            start compensating transaction
            return;
        }

        if(userPaymentDetails == null) {
//            start compensating transaction
            return;
        }
        LOGGER.info("Successfully fetched user payment details for user "+ userPaymentDetails.getFirstName());

        ProcessPaymentCommand processPaymentCommand = new ProcessPaymentCommand(
                UUID.randomUUID().toString(),
                productReservedEvent.getOrderId(),
                userPaymentDetails.getPaymentDetails()
        );

        String result = null;
        try {
            result = commandGateway.sendAndWait(processPaymentCommand, 10, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
//            Start compensating transaction
        }

        if(result == null) {
//            start compensating transaction
            return;
        }
    }
}
