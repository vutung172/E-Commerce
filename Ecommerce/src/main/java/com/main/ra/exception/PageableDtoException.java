package com.main.ra.exception;

import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class PageableDtoException extends RuntimeException{
    private MessageResponse errorMessage;
    private MessageLoader loader;
    private HttpStatus status;

    public PageableDtoException(String messageCode, HttpStatus status) {
        loader = new MessageLoader();
        errorMessage = new MessageResponse(loader.getMessage(messageCode));
        this.status = status;
    }
}
