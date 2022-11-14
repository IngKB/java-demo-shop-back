package com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct;

import com.pruebaclean.demoCl.auth.domain.useCases.signup.SignupResponseModel;
import com.pruebaclean.demoCl.product.domain.entities.Product;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;

import java.math.BigDecimal;

public class RegisterProductInteractor implements RegisterProductBoundary {

    final ProductDsGateway productDsGateway;

    public RegisterProductInteractor(ProductDsGateway productDsGateway) {
        this.productDsGateway = productDsGateway;
    }

    @Override
    public RegisterProductResponseModel register(RegisterProductRequestModel requestModel) {

        Product product = new Product(
                requestModel.getName(),
                requestModel.getSize(),
                requestModel.getDescription(),
                requestModel.getCategory(),
                BigDecimal.valueOf(requestModel.getPrice()),
                requestModel.getStock()
        );
        if(product.isProductValid() != "ok"){
            return new RegisterProductResponseModel(null,product.isProductValid(),1);
        }

        ProductDsRequestModel productGateway = new ProductDsRequestModel(product);

        try {
            productDsGateway.save(productGateway);
            return new RegisterProductResponseModel(productGateway,"Producto guardado exitosamente", 0);
        }catch (Exception e){
            return new RegisterProductResponseModel(productGateway,"Error guardando el producto: " + e.getMessage(), 1);
        }
    }
}
