package com.pruebaclean.demoCl.product.domain.repository;

import com.pruebaclean.demoCl.product.domain.entities.Product;

public class ProductDsRequestModel {
    private String id;

    private String name;

    private String size;

    private String description;

    private String category;

    private Integer price;

    private Integer stock;

    public ProductDsRequestModel() {

    }

    public ProductDsRequestModel(Product product) {
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

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category = category; }

    public void setDescription(String description){ this.description = description; }

    public Integer getPrice(){ return price; }

    public void  setPrice(Integer price){ this.price = price; }

    public Integer getStock(){ return stock; }

    public void setStock(Integer stock){ this.stock = stock; }
}
