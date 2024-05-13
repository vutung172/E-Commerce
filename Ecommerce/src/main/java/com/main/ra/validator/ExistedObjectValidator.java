package com.main.ra.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistedObjectValidator implements ConstraintValidator<ExistedObject,Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
