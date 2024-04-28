package com.main.ra.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
