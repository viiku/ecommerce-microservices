package com.vikku.ProductService.command;

import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")  // Grouping two event handler class in to single group
public class ProductLookupEventsHandler {
}
