package com.vikku.OrderService.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
    OrderEntity findByOrderId(String orderId); // Matches the field name in OrderEntity

    OrderEntity findByOrderIdOrProductId(String orderId, String productId);
}