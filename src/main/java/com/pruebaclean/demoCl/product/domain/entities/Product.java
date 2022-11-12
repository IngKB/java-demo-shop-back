package com.pruebaclean.demoCl.product.domain.entities;

public class Product {

    private String id;

    private String name;

    private String size;

    private String description;

    private String category;

    private Integer price;

    private Integer stock;

    public Product() {

    }

    public Product(
            String name,
            String size,
            String description,
            String category,
            Integer price,
            Integer stock
    ) {
        this.name = name;
        this.size = size;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
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

    public void setSize(){ this.size = size; }

    public  String getDescription(){ return description; }

    public void setDescription(){ this.description = description; }

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category = category; }

    public Integer getPrice(){ return price; }

    public void  setPrice(){ this.price = price; }

    public Integer getStock(){ return stock; }

    public void setStock(){ this.stock = stock; }
}
