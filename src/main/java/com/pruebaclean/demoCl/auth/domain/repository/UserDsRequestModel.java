package com.pruebaclean.demoCl.auth.domain.repository;

import com.pruebaclean.demoCl.auth.domain.entities.Role;
import com.pruebaclean.demoCl.auth.domain.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserDsRequestModel {
    String name;
    String password;
    private String email;
    private Set<RoleDsRequestModel> roles;
    public UserDsRequestModel(String name, String password, String email, Set<RoleDsRequestModel> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
    public UserDsRequestModel(User user) {
        this.name = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        HashSet<RoleDsRequestModel> tempRoles = new HashSet<RoleDsRequestModel>(user.getRoles().size());
        for(Role rol : user.getRoles()) {
            tempRoles.add(new RoleDsRequestModel(rol.getId(),rol.getName()));
        }
        this.roles = tempRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDsRequestModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDsRequestModel> roles) {
        this.roles = roles;
    }
}
