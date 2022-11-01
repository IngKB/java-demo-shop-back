package com.pruebaclean.demoCl.auth.domain.repository;

public interface UserRegisterDsGateway {
    boolean existsByUserName(String name);
    boolean existsByEmail(String name);
    void save(UserDsRequestModel requestModel);
}
