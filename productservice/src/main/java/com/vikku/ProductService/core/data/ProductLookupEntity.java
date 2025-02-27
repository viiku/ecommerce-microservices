package com.vikku.ProductService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Table(name = "productlookup")
@Data
@Entity
public class ProductLookupEntity implements Serializable {

    @Id
    private String productId;

    @Column(unique = true)
    private String title;

}
