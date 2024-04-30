package com.main.ra.advice;

import com.main.ra.exception.BaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;


@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        if (request.getHeader("Authorization").isEmpty() || response.getStatus() == 401 || response.getStatus() == 403){
            BaseException baseException = new BaseException("exception.AuthenticationFailure",HttpStatus.UNAUTHORIZED);
            response.sendError(baseException.getStatus().value(), baseException.getErrorMessage().getMessage());
        }
    }
}
