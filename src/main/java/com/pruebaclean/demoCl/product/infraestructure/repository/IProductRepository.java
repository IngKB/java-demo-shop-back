package com.pruebaclean.demoCl.product.infraestructure.repository;

import com.pruebaclean.demoCl.product.infraestructure.models.ProductDataMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;

public interface IProductRepository extends MongoRepository<ProductDataMapper, Long> {
    Boolean existsByName(String name);
    Boolean existsByCategory(String category);

    @Query("{ 'name' : {'$regex':?0 } }")
    ArrayList<ProductDataMapper> findByName(String name);

    @Query("{ 'name' : {'$regex':?0 } }")
    ArrayList<ProductDataMapper> findByParams(String name);

}
