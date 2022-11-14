package com.pruebaclean.demoCl.auth.domain.useCases.signup;

import com.pruebaclean.demoCl.auth.domain.repository.RoleDsRequestModel;
import com.pruebaclean.demoCl.auth.domain.repository.RoleQueryDsGateway;
import com.pruebaclean.demoCl.auth.domain.repository.UserRegisterDsGateway;
import com.pruebaclean.demoCl.auth.domain.repository.UserDsRequestModel;
import com.pruebaclean.demoCl.auth.domain.entities.ERole;
import com.pruebaclean.demoCl.auth.domain.entities.Role;
import com.pruebaclean.demoCl.auth.domain.entities.User;

import java.util.HashSet;
import java.util.Set;

public class SignupInteractor implements SignupBoundary {

    final UserRegisterDsGateway userDsGateway;
    final RoleQueryDsGateway roleDsGateway;

    public SignupInteractor(UserRegisterDsGateway userDsGateway, RoleQueryDsGateway roleDsGateway) {
        this.userDsGateway = userDsGateway;
        this.roleDsGateway = roleDsGateway;
    }

    @Override
    public SignupResponseModel signup(SignupRequestModel requestModel) {

        if (userDsGateway.existsByUserName(requestModel.getUsername())) {
            return new SignupResponseModel(1,"Error: Username is already taken!");
        }

        if (userDsGateway.existsByEmail(requestModel.getEmail())) {
            return new SignupResponseModel(1,"Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(requestModel.getUsername(),
                requestModel.getEmail(),
                requestModel.getPassword());

        if(!user.usernameIsValid()){
            return new SignupResponseModel(1,"Error: Formato del username es invalido");
        }
        if(!user.emailIsValid()){
            return new SignupResponseModel(1,"Error: Formato del email es invalido");
        }
        if(!user.passwordIsValid()){
            return new SignupResponseModel(1,"Error: Formato del password es invalido");
        }


        Set<String> strRoles = requestModel.getRole();
        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            RoleDsRequestModel userRoleDs = roleDsGateway.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Role userRole = new Role(userRoleDs.getId(), userRoleDs.getName());
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleDsRequestModel adminRoleDs = roleDsGateway.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Role adminRole = new Role(adminRoleDs.getId(),adminRoleDs.getName());
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleDsRequestModel modRoleDs = roleDsGateway.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Role modRole = new Role( modRoleDs.getId(),modRoleDs.getName());
                        roles.add(modRole);

                        break;
                    default:
                        RoleDsRequestModel userRoleDs = roleDsGateway.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Role userRole = new Role(userRoleDs.getId(),userRoleDs.getName());
                        roles.add(userRole);
                }
            });

        }
        user.setRoles(roles);

        UserDsRequestModel userDsModel = new UserDsRequestModel(user);

        userDsGateway.save(userDsModel);

        return new SignupResponseModel(0,"User registered successfully!");
    }

}
