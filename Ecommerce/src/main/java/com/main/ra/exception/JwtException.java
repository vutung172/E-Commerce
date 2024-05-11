package com.main.ra.exception;

import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtException extends RuntimeException {
    private MessageLoader loader;
    private String errorMessage;

    public JwtException(String errorMessage) {
        super(errorMessage);
        this.loader = new MessageLoader();
        this.errorMessage = errorMessage;
    }
}
