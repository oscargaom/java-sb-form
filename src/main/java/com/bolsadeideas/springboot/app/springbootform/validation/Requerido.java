package com.bolsadeideas.springboot.app.springbootform.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RequeridoValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })

public @interface Requerido {
    String message() default "El valor del campo no puede ser vacío";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };    
}