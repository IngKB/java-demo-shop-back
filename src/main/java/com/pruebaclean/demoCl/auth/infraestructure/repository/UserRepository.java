package com.pruebaclean.demoCl.auth.infraestructure.repository;

import com.pruebaclean.demoCl.auth.domain.entities.Role;
import com.pruebaclean.demoCl.auth.domain.repository.RoleDsRequestModel;
import com.pruebaclean.demoCl.auth.domain.repository.UserDsRequestModel;
import com.pruebaclean.demoCl.auth.domain.repository.UserRegisterDsGateway;
import com.pruebaclean.demoCl.auth.infraestructure.models.RoleDataMapper;
import com.pruebaclean.demoCl.auth.infraestructure.models.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

public class UserRepository implements UserRegisterDsGateway {
    final IUserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    public UserRepository(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByUserName(String name) {
        return repository.existsByUsername(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void save(UserDsRequestModel requestModel) {
        UserDataMapper accountDataMapper = new UserDataMapper(
                requestModel.getName(),
                requestModel.getEmail(),
                encoder.encode(requestModel.getPassword())
                );
        HashSet<RoleDataMapper> tempRoles = new HashSet<RoleDataMapper>(requestModel.getRoles().size());
        for(RoleDsRequestModel rol : requestModel.getRoles()) {
            tempRoles.add(new RoleDataMapper(rol.getId(),rol.getName()));
        }
        accountDataMapper.setRoles(tempRoles);
        repository.save(accountDataMapper);
    }
}
