package com.bolsadeideas.springboot.app.springbootform.validation;

import com.bolsadeideas.springboot.app.springbootform.models.domain.Usuario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Usuario usuario = (Usuario)target;

        // ValidationUtils.rejectIfEmptyOrWhitespace (errors, "nombre", "NotEmpty.user.nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace (errors, "nombre", "requerido.user.nombre");

        /* La siguiente validación es exactamente igual a la validación anterior. */
        // if (usuario.getNombre().isEmpty()) {
        //     errors.rejectValue("nombre", "NotEmpty.user.nombre");
        // }

        // if (!usuario.getMatricula().matches("[\\d]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
        //     errors.rejectValue("matricula", "Pattern.user.matricula");
        // }
    }
}
