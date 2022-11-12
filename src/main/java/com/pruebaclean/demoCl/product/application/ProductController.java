package com.pruebaclean.demoCl.product.application;

import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.ProductBoundary;
import com.pruebaclean.demoCl.product.domain.useCases.ProductResponseModel;
import com.pruebaclean.demoCl.product.infraestructure.models.ProductDataMapper;
import com.pruebaclean.demoCl.product.infraestructure.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")

public class ProductController {

    private final ProductBoundary productBoundary;

    private final ProductDsGateway productDsGateway;

    private final ProductRepository productRepository;

    public ProductController(ProductBoundary productBoundary, ProductDsGateway productDsGateway, ProductRepository productRepository) {
        this.productBoundary = productBoundary;
        this.productDsGateway = productDsGateway;
        this.productRepository = productRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDsRequestModel productRequest) {
        String name = productRequest.getName();
        String size = productRequest.getSize();
        String description = productRequest.getDescription();
        String category = productRequest.getCategory();
        Integer price = productRequest.getPrice();
        Integer stock = productRequest.getStock();

        ProductDsRequestModel productDsRequestModel = new ProductDsRequestModel();
        productDsRequestModel.setName(name);
        productDsRequestModel.setDescription(description);
        productDsRequestModel.setCategory(category);
        productDsRequestModel.setSize(size);
        productDsRequestModel.setPrice(price);
        productDsRequestModel.setStock(stock);

        try {
            productDsGateway.save(productDsRequestModel);
            return ResponseEntity.ok(new ProductResponseModel(productDsRequestModel,"Product saved succesfuly", 0));

        }
        catch (Exception e) {
            return ResponseEntity.ok(new ProductResponseModel(productDsRequestModel,"error during product saving:"+ e,1));
        }
    }

    @GetMapping("/list")
    public ArrayList<?> listProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/pagination")
    public Page<ProductDataMapper> filterProducts(Pageable p){
        return productRepository.findAllp(p);
    }

    /*@GetMapping(path="/filter/{name}")*/
    @GetMapping(path="/filter")
    public ArrayList<?> filterProductsByName(
            @RequestParam(name = "name",required=false, defaultValue="null") String productName,
            @RequestParam(name = "size",required=false, defaultValue="null") String size,
            @RequestParam(name = "category",required=false, defaultValue="null") String category,
            @RequestParam(name = "price",required=false, defaultValue="null") Integer price,
            @RequestParam(name = "stock",required=false, defaultValue="null") Integer stock
    ){
        /*if(productName != "null") return productRepository.filterByName(productName);*/
        return productRepository.filterWithParams(productName, size, category, price, stock);
    }
}
