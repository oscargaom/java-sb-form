package com.bolsadeideas.springboot.app.springbootform.services;

import java.util.List;

import com.bolsadeideas.springboot.app.springbootform.models.domain.Role;

public interface RoleService {

    public List<Role> listar();

    public Role obtenerPorId(Integer id);
}