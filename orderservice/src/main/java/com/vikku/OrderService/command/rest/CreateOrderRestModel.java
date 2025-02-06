package com.vikku.OrderService.command.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateOrderRestModel {

//    @NotBlank(message = "Product ID is a required field")
    private String productId;

    @Min(value = 1, message = "Quantity can not be lower than 1")
    @Max(value = 5, message = "Quantity can not be greater than 5")
    private int quantity;

    @NotBlank(message = "Address Id is a required field")
    private String addressId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
