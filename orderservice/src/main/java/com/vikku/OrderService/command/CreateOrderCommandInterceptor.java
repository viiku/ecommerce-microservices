package com.vikku.OrderService.command;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class CreateOrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CreateOrderCommandInterceptor.class);
    
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            LOGGER.info("Intercepted command: "+ command.getPayload());
                    
//            checking if command payload is equal to CreateOrderCommand class
            if(CreateOrderCommand.class.equals(command.getPayloadType())) {

                CreateOrderCommand createOrderCommand = (CreateOrderCommand)command.getPayload();
                
                if(createOrderCommand.getProductId() == null || createOrderCommand.getProductId().isBlank()) {
                    throw new IllegalArgumentException("Product ID can not be empty.");
                }

                if (createOrderCommand.getAddressId() == null || createOrderCommand.getAddressId().isBlank()) {
                    throw new IllegalArgumentException("Address Id can not be empty.");
                }

                if (createOrderCommand.getQuantity() <= 0) {
                    throw new IllegalArgumentException("Quantity can not be less than one.");
                }
            }
            return command;
        };
    }
}
