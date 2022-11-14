package com.pruebaclean.demoCl.product.domain.useCases.ListProducts;

import java.math.BigDecimal;

public class ListProductsRequestModel {

    private String name;
    private String size;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer page;
    private Integer pageSize;

    public ListProductsRequestModel(String productName, String size, String category, BigDecimal minPrice, BigDecimal maxPrice, Integer page, Integer pageSize) {
        this.name = productName;
        this.size = size;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
