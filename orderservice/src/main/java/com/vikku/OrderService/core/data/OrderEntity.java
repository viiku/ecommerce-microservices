package com.vikku.OrderService.core.data;

import com.vikku.OrderService.command.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;


@Table(name="orders")
@Entity
@Data
public class OrderEntity implements Serializable {

//    private static final long serialVersionUID = -222223838282828L;

    @Id
    @Column(unique = true)
    public String orderId;

    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
