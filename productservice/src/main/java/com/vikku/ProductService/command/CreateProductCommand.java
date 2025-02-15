package com.vikku.ProductService.command;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductCommand {

    private final String UUID;
    private final String title;
    private final BigDecimal price;
    private final Integer quantity;

    public CreateProductCommand(String UUID, String title, BigDecimal price, Integer quantity) {
        this.UUID = UUID;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUUID() {
        return UUID;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
