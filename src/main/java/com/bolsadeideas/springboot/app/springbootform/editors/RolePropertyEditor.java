package com.bolsadeideas.springboot.app.springbootform.editors;

import java.beans.PropertyEditorSupport;

import com.bolsadeideas.springboot.app.springbootform.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;


    @Override
    public void setAsText(String roId) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(roId);
            setValue(roleService.obtenerPorId(id));
        } catch (Exception e) {
            setValue(null);
        }
    }

    
}