package com.pruebaclean.demoCl.auth.infraestructure.repository;


import java.util.Optional;

import com.pruebaclean.demoCl.auth.infraestructure.models.RoleDataMapper;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface IRoleRepository extends MongoRepository<RoleDataMapper, Long> {
	Optional<RoleDataMapper> findByName(String name);
}
