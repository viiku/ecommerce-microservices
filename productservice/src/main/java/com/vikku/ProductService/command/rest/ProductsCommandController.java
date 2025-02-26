package com.vikku.ProductService.command.rest;

import com.vikku.ProductService.command.CreateProductCommand;
import io.grpc.netty.shaded.io.netty.util.collection.CharObjectHashMap;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    private final Environment environment;
    private final CommandGateway commandGateway;

    @Autowired
    ProductsCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = new CreateProductCommand(
                UUID.randomUUID().toString(),
                createProductRestModel.getTitle(),
                createProductRestModel.getPrice(),
                createProductRestModel.getQuantity()
        );

        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }
        return returnValue;
    }
}
