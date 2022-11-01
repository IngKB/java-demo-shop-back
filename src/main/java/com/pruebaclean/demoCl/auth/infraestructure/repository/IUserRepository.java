package com.pruebaclean.demoCl.auth.infraestructure.repository;

import java.util.Optional;

import com.pruebaclean.demoCl.auth.infraestructure.models.UserDataMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserDataMapper, Long> {
	Optional<UserDataMapper> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}