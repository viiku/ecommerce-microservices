package com.vikku.productservice.query;

import com.vikku.productservice.core.data.ProductEntity;
import com.vikku.productservice.core.data.ProductsRepository;
import com.vikku.productservice.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsQueryHandler {

    private final ProductsRepository productRepository;

    ProductsQueryHandler(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductRestModel> productRest = new ArrayList<>();

        List<ProductEntity> storedProducts = productRepository.findAll();

        for(ProductEntity productEntity: storedProducts) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRest.add(productRestModel);
        }

        return productRest;
    }
}
