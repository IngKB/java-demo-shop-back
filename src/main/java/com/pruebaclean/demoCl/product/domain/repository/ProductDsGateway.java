package com.pruebaclean.demoCl.product.domain.repository;

import com.pruebaclean.demoCl.product.domain.useCases.ListProducts.ListProductsRequestModel;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface ProductDsGateway {
    void save (ProductDsRequestModel requestModel);

    ArrayList<ProductDsRequestModel> findAll(ListProductsRequestModel requestModel);

    ProductDsRequestModel findById(String id);

}
