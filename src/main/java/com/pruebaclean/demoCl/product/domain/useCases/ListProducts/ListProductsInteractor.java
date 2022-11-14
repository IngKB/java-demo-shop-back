package com.pruebaclean.demoCl.product.domain.useCases.ListProducts;

import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;

import java.util.ArrayList;

public class ListProductsInteractor implements ListProductsBoundary{

    final ProductDsGateway productDsGateway;

    public ListProductsInteractor(ProductDsGateway productDsGateway) {
        this.productDsGateway = productDsGateway;
    }

    @Override
    public ListProductsResponseModel list(ListProductsRequestModel requestModel) {
        try {
            ArrayList<ProductDsRequestModel> products = productDsGateway.findAll(requestModel);
            return new ListProductsResponseModel(products,"",0);
        }catch (Exception e){
            return new ListProductsResponseModel(null,"Error al retribuir productos: "+e.getMessage(),1);
        }
    }
}
