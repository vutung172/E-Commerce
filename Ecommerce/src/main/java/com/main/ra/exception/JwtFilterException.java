package com.main.ra.exception;

import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class JwtFilterException extends RuntimeException{
    private MessageLoader loader;
    private MessageResponse errorMessage;
    private HttpStatus status;

    public JwtFilterException(String errorCode, HttpStatus status) {
        super(errorCode);
        this.loader = new MessageLoader();
        this.errorMessage = new MessageResponse(loader.getMessage(errorCode));
        this.status = status;
    }
}
