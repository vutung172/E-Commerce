package com.main.ra.advice;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.JwtFilterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException,BaseException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null ) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("khong co auth");
        }
        var e = authenticationException.getCause();
        JwtFilterException jwt;
        if (e instanceof JwtFilterException){
            jwt = (JwtFilterException) e;
            response.sendError(HttpStatus.UNAUTHORIZED.value(),jwt.getErrorMessage().getMessage());
        }
    }
}
