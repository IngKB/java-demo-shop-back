package com.pruebaclean.demoCl.product.infraestructure.models;

import com.pruebaclean.demoCl.product.domain.entities.Product;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
public class ProductDataMapper {

    @Id
    private String id;

    private String name;

    private String size;

    private String category;

    private String description;

    private BigDecimal price;

    private Integer stock;

    public ProductDataMapper() {

    }

    public ProductDataMapper(ProductDsRequestModel product) {
        this.id = product.getId();
        this.name = product.getName();
        this.size = product.getSize();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name){ this.name = name; }

    public String getSize(){ return size; }

    public void setSize(String size){ this.size = size; }

    public  String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category = category; }

    public BigDecimal getPrice(){ return price; }

    public void  setPrice(BigDecimal price){ this.price = price; }

    public Integer getStock(){ return stock; }

    public void setStock(Integer stock){ this.stock = stock; }
}
