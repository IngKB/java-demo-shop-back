package com.pruebaclean.demoCl.product.domain.useCases.ListProducts;

import java.util.ArrayList;

public class ListProductsResponseModel {
    private ArrayList<?> Products;
    private String message;
    private Integer code;

    public ListProductsResponseModel(ArrayList<?> products, String message, Integer code) {
        Products = products;
        this.message = message;
        this.code = code;
    }

    public ArrayList<?> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<?> products) {
        Products = products;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
