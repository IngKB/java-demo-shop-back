package com.pruebaclean.demoCl.product.infraestructure.repository;

import com.pruebaclean.demoCl.product.domain.repository.ProductDsGateway;
import com.pruebaclean.demoCl.product.domain.repository.ProductDsRequestModel;
import com.pruebaclean.demoCl.product.domain.useCases.ListProducts.ListProductsRequestModel;
import com.pruebaclean.demoCl.product.infraestructure.models.ProductDataMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductRepository implements ProductDsGateway {
    final IProductRepository repository;

    final MongoOperations mongoOperations;

    public ProductRepository(IProductRepository repository, MongoOperations mongoOperations) {
        this.repository = repository;
        this.mongoOperations = mongoOperations;
    }

    public boolean existsByEmail(String name){ return repository.existsByName(name);}

    public boolean existsByCategory(String category) {return repository.existsByCategory(category);}

    @Override
    public void save(ProductDsRequestModel requestModel) {

        ProductDataMapper productDataMapper = new ProductDataMapper(requestModel);
        repository.save(productDataMapper);
    }

    @Override
    public ArrayList<ProductDsRequestModel> findAll(ListProductsRequestModel requestModel) {
        Pageable paging = PageRequest.of(requestModel.getPage(), requestModel.getPageSize());

        Query query = new Query();
        if(!requestModel.getName().isEmpty()){
            query.addCriteria(Criteria.where("name").regex("^"+requestModel.getName()));
        }
        if(!requestModel.getCategory().isEmpty()){
            query.addCriteria(Criteria.where("category").regex("^"+requestModel.getCategory()));
        }
        if(!requestModel.getSize().isEmpty()){
            query.addCriteria(Criteria.where("size").regex("^"+requestModel.getSize()));
        }
        query.with(paging);

        ArrayList<ProductDsRequestModel> results = new ArrayList<ProductDsRequestModel>();
        List<ProductDataMapper> repositoryResults = mongoOperations.find(query,ProductDataMapper.class);

        for (ProductDataMapper rp : repositoryResults) {
            results.add(new ProductDsRequestModel(
                    rp.getId(),
                    rp.getName(),
                    rp.getSize(),
                    rp.getDescription(),
                    rp.getCategory(),
                    rp.getPrice(),
                    rp.getStock()));
        }
        return results;
    }

    @Override
    public ProductDsRequestModel findById(String id) {
        ProductDataMapper pDM = repository.findById(id).orElse(null);
        if(pDM!=null){
            return new ProductDsRequestModel(
                    pDM.getId(),
                    pDM.getName(),
                    pDM.getSize(),
                    pDM.getDescription(),
                    pDM.getCategory(),
                    pDM.getPrice(),
                    pDM.getStock());
        }
        return null;
    }


}
