package com.pruebaclean.demoCl.product.domain.useCases;

import com.pruebaclean.demoCl.product.domain.entities.Product;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;

import java.lang.reflect.Array;

public class ProductResponseModel {

    private String message;

    private ProductDsRequestModel data;

    private Integer code;

    public ProductResponseModel(ProductDsRequestModel data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public Object getData(){ return data; }

    public void setData(ProductDsRequestModel data){ this.data = data; }

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
