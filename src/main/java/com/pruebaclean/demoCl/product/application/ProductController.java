package com.pruebaclean.demoCl.product.application;

import com.pruebaclean.demoCl.product.domain.useCases.ListProducts.ListProductsBoundary;
import com.pruebaclean.demoCl.product.domain.useCases.ListProducts.ListProductsRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.ListProducts.ListProductsResponseModel;
import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductBoundary;
import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.RegisterProduct.RegisterProductResponseModel;
import com.pruebaclean.demoCl.product.domain.useCases.UpdateProduct.UpdateProductBoundary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")

public class ProductController {

    private final RegisterProductBoundary registerProductBoundary;
    private final ListProductsBoundary listProductBoundary;
    private final UpdateProductBoundary updateProductBoundary;

    public ProductController(RegisterProductBoundary productBoundary, ListProductsBoundary listProductBoundary, UpdateProductBoundary updateProductBoundary) {
        this.registerProductBoundary = productBoundary;
        this.listProductBoundary = listProductBoundary;
        this.updateProductBoundary = updateProductBoundary;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody RegisterProductRequestModel productRequest) {

        RegisterProductResponseModel response = registerProductBoundary.register(productRequest);
        if (response.getCode() == 1) {
            return ResponseEntity
                    .badRequest()
                    .body(response.getMessage());
        }
        return ResponseEntity.ok(response.getMessage());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody RegisterProductRequestModel productRequest) {

        RegisterProductResponseModel response = updateProductBoundary.update(productRequest);
        if (response.getCode() == 1) {
            return ResponseEntity
                    .badRequest()
                    .body(response.getMessage());
        }
        return ResponseEntity.ok(response.getMessage());
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProducts(
            @RequestParam(name = "name",required=false, defaultValue="") String productName,
            @RequestParam(name = "size",required=false, defaultValue="") String size,
            @RequestParam(name = "category",required=false, defaultValue="") String category,
            @RequestParam(name = "minprice",required=false, defaultValue="0") Float minPrice,
            @RequestParam(name = "maxprice",required=false, defaultValue="0") Float maxPrice,
            @RequestParam(name = "page",required=false, defaultValue="0") Integer page,
            @RequestParam(name = "pagesize",required=false, defaultValue="20") Integer pagesize
    ){
        ListProductsRequestModel request = new ListProductsRequestModel(productName,size,
                category, BigDecimal.valueOf(minPrice),BigDecimal.valueOf(maxPrice), page,pagesize);
        ListProductsResponseModel response = listProductBoundary.list(request);
        if (response.getCode() == 1) {
            return ResponseEntity
                    .badRequest()
                    .body(response.getMessage());
        }
        return ResponseEntity.ok(response.getProducts());
    }

}
