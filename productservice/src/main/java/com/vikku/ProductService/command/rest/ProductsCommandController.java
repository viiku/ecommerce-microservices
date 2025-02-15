package com.vikku.ProductService.command.rest;

import com.vikku.ProductService.command.CreateProductCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = new CreateProductCommand(
                UUID.randomUUID().toString(),
                createProductRestModel.getTitle(),
                createProductRestModel.getPrice(),
                createProductRestModel.getQuantity()
        );

        return "test";
    }
}
