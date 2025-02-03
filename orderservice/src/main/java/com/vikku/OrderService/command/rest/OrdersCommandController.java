package com.vikku.OrderService.command.rest;

import com.vikku.OrderService.command.CreateOrderCommand;
import com.vikku.OrderService.command.OrderStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    @PostMapping
    public void getOrders(@Valid @RequestBody CreateOrderRestModel createOrderRestModel) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                UUID.randomUUID().toString(),
                "27b95829-4f3f-4ddf8983-151ba010e35b",
                createOrderRestModel.getProductId(),
                createOrderRestModel.getQuantity(),
                createOrderRestModel.getAddressId(),
                OrderStatus.CREATED
        );
    }
}
