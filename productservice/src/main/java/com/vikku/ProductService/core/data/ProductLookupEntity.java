package com.vikku.ProductService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Table(name = "productlookup")
@Entity
public class ProductLookupEntity implements Serializable {

    private static final long serialVersionUID = 222223838282828L;

    @Id
    private String productId;

    @Column(unique = true)
    private String title;

    public ProductLookupEntity() {}

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
