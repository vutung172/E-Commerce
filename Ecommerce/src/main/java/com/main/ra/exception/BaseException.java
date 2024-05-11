package com.main.ra.exception;


import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private MessageResponse errorMessage;
    private MessageLoader loader;
    private HttpStatus status;


    public BaseException(String errorCode, HttpStatus status) {
        this.loader = new MessageLoader();
        this.errorMessage = new MessageResponse(loader.getMessage(errorCode));
        this.status = status;
    }
}
