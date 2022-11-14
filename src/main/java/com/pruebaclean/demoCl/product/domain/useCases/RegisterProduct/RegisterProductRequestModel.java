package com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct;

public class RegisterProductRequestModel {

    private String id;
    private String name;

    private String size;

    private String description;

    private String category;

    private Float price;

    private Integer stock;

    public RegisterProductRequestModel(
            String name,
            String size,
            String description,
            String category,
            Float price,
            Integer stock
    ) {
        this.name = name;
        this.size = size;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }

    public void setName(String name){ this.name = name; }

    public String getSize(){ return size; }

    public void setSize(){ this.size = size; }

    public  String getDescription(){ return description; }

    public void setDescription(){ this.description = description; }

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category = category; }

    public Float getPrice(){ return price; }

    public void  setPrice(){ this.price = price; }

    public Integer getStock(){ return stock; }

    public void setStock(){ this.stock = stock; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

