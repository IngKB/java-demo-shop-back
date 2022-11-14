package com.pruebaclean.demoCl.product.domain.useCases.UpdateProduct;

import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductResponseModel;

import java.math.BigDecimal;

public class UpdateProductInteractor implements UpdateProductBoundary {
    final ProductDsGateway productDsGateway;

    public UpdateProductInteractor(ProductDsGateway productDsGateway) {
        this.productDsGateway = productDsGateway;
    }

    @Override
    public RegisterProductResponseModel update(RegisterProductRequestModel requestModel) {

        ProductDsRequestModel productDs = productDsGateway.findById(requestModel.getId());
        if(productDs!=null){
            productDs.setName(requestModel.getName());
            productDs.setCategory(requestModel.getCategory());
            productDs.setDescription(requestModel.getDescription());
            productDs.setSize(requestModel.getSize());
            productDs.setPrice(BigDecimal.valueOf(requestModel.getPrice()));
            productDs.setStock(requestModel.getStock());

            try {
                productDsGateway.save(productDs);
                return new RegisterProductResponseModel(productDs,"Producto actualizado exitosamente", 0);
            }catch (Exception e){
                return new RegisterProductResponseModel(productDs,"Error actualizando el producto: " + e.getMessage(), 1);
            }
        }
        return new RegisterProductResponseModel(null,"No se encontró el producto",1);
    }
}
