package com.main.ra.advice;

import com.main.ra.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity handleUserInfoException(BaseException be){
        return new ResponseEntity<>(be.getErrorMessage(), be.getStatus());
    }


}
