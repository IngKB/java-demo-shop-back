package com.pruebaclean.demoCl.product.domain.useCases;

import com.pruebaclean.demoCl.product.domain.entities.Product;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;
import org.springframework.context.annotation.Bean;

public class ProductInteractor implements ProductBoundary{

    final ProductDsGateway productDsGateway;

    public ProductInteractor(ProductDsGateway productDsGateway) {
        this.productDsGateway = productDsGateway;
    }

    @Override
    public ProductResponseModel register(ProductRequestModel requestModel) {

        Product product = new Product(
                requestModel.getName(),
                requestModel.getSize(),
                requestModel.getDescription(),
                requestModel.getCategory(),
                requestModel.getPrice(),
                requestModel.getStock()
        );
        ProductDsRequestModel productGateway = new ProductDsRequestModel(product);

        productDsGateway.save(productGateway);

        return new ProductResponseModel(productGateway,"product save successfully", 0);
    }
}
