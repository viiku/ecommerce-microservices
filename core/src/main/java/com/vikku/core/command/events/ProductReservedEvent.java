package com.vikku.core.command.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ProductReservedEvent {
    @TargetAggregateIdentifier
    private final String productId;

    private final Integer quantity;
    private final String orderId;
    private final String userId;

    public ProductReservedEvent(String productId, Integer quantity, String orderId, String userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }
}
