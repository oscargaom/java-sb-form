package com.bolsadeideas.springboot.app.springbootform.editors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.bolsadeideas.springboot.app.springbootform.models.domain.Pais;
import com.bolsadeideas.springboot.app.springbootform.services.PaisService;

import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl implements PaisService {

    private List<Pais> listaPaises;

    @PostConstruct()
    public void onConstruct(){
        System.out.println("------------- PaisServiceImpl loaded");
    }


    public PaisServiceImpl() {
        listaPaises = Arrays.asList(new Pais(1, "MX", "México"), new Pais(2, "ES", "España"),
                new Pais(3, "CO", "Colombia"), new Pais(4, "AR", "Argentina"), new Pais(5, "PE", "Peru"),
                new Pais(6, "HO", "Honduras"), new Pais(7, "EL", "El Salvador"));
    }

    @Override
    public List<Pais> listar() {
        return listaPaises;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        System.out.println("obtenerPorId: ".concat(id.toString()));
        Optional<Pais> pais = listaPaises.stream().filter(p -> p.getId().equals(id)).findFirst();
        System.out.println("obtenerPorId: ".concat(pais.toString()));

        return pais.isPresent() ? pais.get() : null;
    }
}