package com.main.ra.exception;

import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;

@Getter
@Setter
public class JwtFilterException extends RuntimeException{
    private MessageLoader loader;
    private MessageResponse errorMessage;
    private HttpStatus status;

    public JwtFilterException(String errorCode, HttpStatus status) {
        super();
        this.loader = new MessageLoader();
        this.errorMessage = new MessageResponse(loader.getMessage(errorCode));
        this.status = status;
    }

}
