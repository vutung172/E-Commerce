package com.main.ra.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleUserInfoException(BaseException be){
        return new ResponseEntity<>(be.getErrorMessage(), be.getStatus());
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseBody
    public ResponseEntity<MessageResponse> handleException(Exception e){
        return new ResponseEntity<>(MessageResponse.builder().message(e.getMessage()).build(), HttpStatus.FORBIDDEN);
    }

}
