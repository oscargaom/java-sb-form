package com.bolsadeideas.springboot.app.springbootform.services;

import java.util.List;

import com.bolsadeideas.springboot.app.springbootform.models.domain.Pais;

public interface PaisService {
    
    public List<Pais> listar();

    public Pais obtenerPorId(Integer id);
}