package com.pruebaclean.demoCl.product.infraestructure.repository;

import com.pruebaclean.demoCl.product.domain.entities.Product;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;
import com.pruebaclean.demoCl.product.infraestructure.models.ProductDataMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;


public class ProductRepository implements ProductDsGateway {
    final IProductRepository repository;

    public ProductRepository(IProductRepository repository) {
        this.repository = repository;
    }

    public boolean existsByEmail(String name){ return repository.existsByName(name);}

    public boolean existsByCategory(String category) {return repository.existsByCategory(category);}

    @Override
    public void save(ProductDsRequestModel requestModel) {

        Product product = new Product(
                requestModel.getName(),
                requestModel.getSize(),
                requestModel.getDescription(),
                requestModel.getCategory(),
                requestModel.getPrice(),
                requestModel.getStock()
        );

        ProductDataMapper productDataMapeer = new ProductDataMapper(product);

        repository.save(productDataMapeer);
    }

    @Override
    public ArrayList<?> findAll() {
        return (ArrayList<?>) repository.findAll();
    }

    public Page<ProductDataMapper> findAllp(Pageable p){

        return  repository.findAll(p);
    }

    public ArrayList<?> filterByName(String name) { return repository.findByName(name); }

    public ArrayList<?> filterWithParams(
            String productName,
            String size,
            String category,
            Integer price,
            Integer stock) { return repository.findByParams(productName); }
     /*return productRepository.filterWithParams(productName, size, category, price, stock);*/


}
