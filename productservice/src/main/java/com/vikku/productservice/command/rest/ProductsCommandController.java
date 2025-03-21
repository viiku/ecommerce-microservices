package com.vikku.productservice.command.rest;

import com.vikku.productservice.command.CreateProductCommand;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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
        returnValue = commandGateway.sendAndWait(createProductCommand);
//        try {
//            returnValue = commandGateway.sendAndWait(createProductCommand);
//        } catch (Exception e) {
//            returnValue = e.getLocalizedMessage();
//        }
        return returnValue;
    }
}
