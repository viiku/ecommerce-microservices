package com.vikku.OrderService.command.rest;

import com.vikku.OrderService.command.CreateOrderCommand;
import com.vikku.OrderService.command.OrderStatus;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    private final Environment environment;
    private final CommandGateway commandGateway;

    public OrdersCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }
    @PostMapping
    public String getOrders(@Valid @RequestBody CreateOrderRestModel createOrderRestModel) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                UUID.randomUUID().toString(),
                "27b95829-4f3f-4ddf8983-151ba010e35b",
                createOrderRestModel.getProductId(),
                createOrderRestModel.getQuantity(),
                createOrderRestModel.getAddressId(),
                OrderStatus.CREATED
        );

        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(createOrderCommand);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }

        return returnValue;
    }
}
