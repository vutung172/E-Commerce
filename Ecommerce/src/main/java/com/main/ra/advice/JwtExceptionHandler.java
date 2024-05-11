package com.main.ra.advice;

import com.main.ra.exception.JwtException;
import com.main.ra.exception.JwtFilterException;
import jakarta.servlet.ServletException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class JwtExceptionHandler extends ResponseEntityExceptionHandler {
   /* @ExceptionHandler(value = { AuthenticationException.class})
    @ResponseBody
    public ResponseEntity handleServletException(ServletException se){
        return new ResponseEntity<>(se.getRootCause().getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ServletException.class})
    @ResponseBody
    public ResponseEntity handleJwtException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
    }*/
}
