package com.vikku.productservice.command;

import com.vikku.productservice.core.data.ProductLookupEntity;
import com.vikku.productservice.core.data.ProductLookupRepository;
import com.vikku.productservice.core.events.ProductCreatedEvent;
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
            ProductLookupEntity productLookupEntity = new ProductLookupEntity(productCreatedEvent.getProductId(),
                    productCreatedEvent.getTitle());

            productLookupRepository.save(productLookupEntity);
    }

}
