package com.pruebaclean.demoCl.auth.infraestructure.repository;

import com.pruebaclean.demoCl.auth.domain.repository.RoleDsRequestModel;
import com.pruebaclean.demoCl.auth.domain.repository.RoleQueryDsGateway;
import com.pruebaclean.demoCl.auth.infraestructure.models.RoleDataMapper;

import java.util.Optional;

public class RoleRepository implements RoleQueryDsGateway {

    final IRoleRepository repository;

    public RoleRepository(IRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<RoleDsRequestModel> findByName(String name) {
        RoleDsRequestModel roleDsRequestModel;
        try{
            RoleDataMapper roleDM = repository.findByName(name).orElseThrow(()-> new RuntimeException("Error: Role is not found."));
            roleDsRequestModel = new RoleDsRequestModel(roleDM.getId(),roleDM.getName());
        }catch(Exception e){
            System.out.println(e);
            roleDsRequestModel = null;
        }
        return Optional.of(roleDsRequestModel);
    }
}
