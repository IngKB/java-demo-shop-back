package com.pruebaclean.demoCl.product.domain.useCases.UpdateProduct;

import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductResponseModel;

public interface UpdateProductBoundary {
    RegisterProductResponseModel update(RegisterProductRequestModel requestModel);
}
