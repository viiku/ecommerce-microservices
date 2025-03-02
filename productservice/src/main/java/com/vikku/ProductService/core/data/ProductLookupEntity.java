package com.vikku.ProductService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "productlookup")
@Data
@Entity
public class ProductLookupEntity implements Serializable {

    @Id
    private String productId;

    @Column(unique = true)
    private String title;

    public ProductLookupEntity(String productId, String title) {
        this.productId = productId;
        this.title = title;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
