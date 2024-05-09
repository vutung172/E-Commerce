package com.main.ra.validator;

import com.main.ra.model.dto.request.SignUpRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator implements ConstraintValidator<PasswordConfirm, Object>{
    private String field;
    private String fieldConfirm;
    private Object clazz;

    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        try{
            this.field = constraintAnnotation.field();
            this.fieldConfirm = constraintAnnotation.fieldConfirm();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        String fieldValue  = (String) new BeanWrapperImpl(object).getPropertyValue(field);
        String fieldConfirmValue  = (String) new BeanWrapperImpl(object).getPropertyValue(fieldConfirm);
        System.out.println(fieldValue);
        return fieldValue != null && fieldValue.equals(fieldConfirmValue);
    }
}
