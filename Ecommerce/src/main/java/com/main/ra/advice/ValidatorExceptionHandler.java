package com.main.ra.advice;

import com.main.ra.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class ValidatorExceptionHandler {
    @Autowired
    private CommonValidator commonValidator;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> validated =  commonValidator.validate(result);
        Map<String,String> confirmPassword = commonValidator.confirmPassword(result);
        validated.putAll(confirmPassword);
        return new ResponseEntity<>(validated, HttpStatus.BAD_REQUEST);
    }

}
