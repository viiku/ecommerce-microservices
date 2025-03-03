package com.vikku.ProductService.command;

import com.vikku.ProductService.core.data.ProductLookupEntity;
import com.vikku.ProductService.core.data.ProductLookupRepository;
import com.vikku.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")  // Grouping two event handler class in to single group
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        try {
            ProductLookupEntity productLookupEntity = new ProductLookupEntity(productCreatedEvent.getProductId(),
                    productCreatedEvent.getTitle());

            productLookupRepository.save(productLookupEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
