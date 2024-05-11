package com.main.ra.advice;

import com.main.ra.exception.JwtFilterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(value = { ServletException.class})
    @ResponseBody
    public ResponseEntity handleServletException(ServletException se){
        return new ResponseEntity<>(se.getRootCause().getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {JwtFilterException.class})
    @ResponseBody
    public ResponseEntity handleJwtException(JwtFilterException je){
        return new ResponseEntity<>(je.getErrorMessage(),HttpStatus.UNAUTHORIZED);
    }
}
