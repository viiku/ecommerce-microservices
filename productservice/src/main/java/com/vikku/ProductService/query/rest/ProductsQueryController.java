package com.vikku.ProductService.query.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

    private ProductRestModel productRestModel;

    @GetMapping
    public String getProducts(ProductRestModel productRestModel) {
        return "Test from query side";
    }

}
