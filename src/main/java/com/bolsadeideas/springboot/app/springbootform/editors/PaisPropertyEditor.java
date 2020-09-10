package com.bolsadeideas.springboot.app.springbootform.editors;

import java.beans.PropertyEditorSupport;

import javax.annotation.PostConstruct;

import com.bolsadeideas.springboot.app.springbootform.models.domain.Pais;
import com.bolsadeideas.springboot.app.springbootform.services.PaisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*  Esta clase tiene que ser de tipo @Component para que podamos inyectar PaisService
*/
@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private PaisService paisService;
    
    @PostConstruct()
    public void onConstruct(){
        System.out.println("------------- PaisPropertyEditor loaded");
    }

    @Override
    public void setAsText(String idFromForm) {
        
        try {
            System.out.println("*******************: ".concat(idFromForm));
            Integer id = Integer.parseInt(idFromForm);
            System.out.println("*********: ".concat(id.toString()));

            if (paisService == null) {
                System.out.println("paisService null");
            }

            Pais p = paisService.obtenerPorId(id);
            System.out.println(p);

            this.setValue(p);
        } catch (NumberFormatException e) {
            System.out.println(e);
            this.setValue(null);
        }
    }

    
}