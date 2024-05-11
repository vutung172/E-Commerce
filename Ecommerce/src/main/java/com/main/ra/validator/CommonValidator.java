package com.main.ra.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CommonValidator {

    public Map<String, String> validate(BindingResult result){
        Map<String,String> fails = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(fe -> {
            fails.put(fe.getField(), fe.getField() +" "+ fe.getDefaultMessage());
        });
        return fails;
    }

    public Map<String, String> confirmPassword(BindingResult result){
        Map<String,String> fails = new HashMap<>();
        List<ObjectError> errors = result.getAllErrors();
        errors.stream().filter(e -> Objects.equals(e.getCode(), "PasswordConfirm")).forEach(e -> {
            fails.put(e.getCode(), e.getDefaultMessage());
        });
        return fails;
    }
}
