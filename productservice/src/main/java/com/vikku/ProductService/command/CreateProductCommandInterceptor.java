package com.vikku.ProductService.command;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Lazy
@Component
@DependsOn("commandBus")
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

//        private final OrderLookupRepository orderLookupRepository;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

//    public CreateProductCommandInterceptor(OrderLookupRepository orderLookupRepository) {
//        this.orderLookupRepository = orderLookupRepository;
//    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            LOGGER.info("Intercepted command: " + command.getPayload());

//            checking if command payload is equal to CreateProductCommand class
            if (CreateProductCommand.class.equals(command.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

//                Since we're already doing Bean validation,
//                we can actually remove this validation

                if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price can not be less than zero.");
                }

                if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
                    throw new IllegalArgumentException("Title can not be empty.");
                }

                if (createProductCommand.getQuantity() <= 0) {
                    throw new IllegalArgumentException("Quantity can not be less than one.");
                }

//                checking if the orderId or productId already exist in database
//                OrderLookupEntity orderLookupEntity = orderLookupRepository.findByOrderIdOrProductId(createOrderCommand.getOrderId(),
//                        createOrderCommand.getProductId());
//
//                if(orderLookupEntity != null) {
//                    throw new IllegalStateException(
//                            String.format("Order with orderId %s or productId %s already exist",
//                                    createOrderCommand.getOrderId(), createOrderCommand.getProductId()
//                            )
//                    );
//                }
            }

            return command;
        };
    }
}
