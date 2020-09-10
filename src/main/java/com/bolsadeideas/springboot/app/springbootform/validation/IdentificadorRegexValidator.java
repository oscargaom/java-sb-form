package com.bolsadeideas.springboot.app.springbootform.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificadorRegexValidator implements ConstraintValidator<IdentificadorRegex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value.matches("[\\d]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}");
    }
    
}
