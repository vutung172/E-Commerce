package com.main.ra.advice;

import com.main.ra.exception.BaseException;
import com.main.ra.exception.JwtFilterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.io.IOException;

@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException,BaseException {
        if (authenticationException != null){
            throw new JwtFilterException("exception.authentication.Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

}
