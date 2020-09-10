package com.bolsadeideas.springboot.app.springbootform.editors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bolsadeideas.springboot.app.springbootform.models.domain.Role;
import com.bolsadeideas.springboot.app.springbootform.services.RoleService;

import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    private List<Role> roles = new ArrayList<>();
 
    public RoleServiceImpl() {
        roles.add(new Role(1, "Moderador", "ROL_MODERATOR"));
        roles.add(new Role(2, "Usuario", "ROL_USER"));
        roles.add(new Role(3, "Administrador", "ROL_ADMIN"));
    }

    @Override
    public List<Role> listar() {
        return this.roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {
        Optional<Role> role = this.roles.stream().filter(r -> r.getId().equals(id)).findFirst();

        return role.isPresent() ? role.get() : null;

    }
}