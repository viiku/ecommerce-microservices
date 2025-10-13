package com.vikku.orderservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLookupRepository extends JpaRepository<OrderLookupEntity, String> {

    OrderLookupEntity findByOrderIdOrProductId(String orderId, String productId);
}
