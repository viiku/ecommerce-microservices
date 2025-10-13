package com.vikku.orderservice.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Table(name="orderlookup")
@Entity
public class OrderLookupEntity implements Serializable {

    private static final long serialVersionUID = 222223838282828L;

    @Id
    @Column(unique = true)
    private String orderId;
    private String productId;
//
//    @Repository
//    public static interface OrderLookupRepository extends JpaRepository<OrderLookupEntity, String> {
//        OrderLookupEntity findByOrderIdOrProductId(String orderId, String productId);
//    }
}
