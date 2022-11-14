package com.pruebaclean.demoCl.auth.domain.repository;

import java.util.Optional;

public interface RoleQueryDsGateway {
    Optional<RoleDsRequestModel> findByName(String name);
}
