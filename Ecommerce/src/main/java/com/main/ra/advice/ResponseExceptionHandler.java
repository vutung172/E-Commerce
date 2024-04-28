package com.main.ra.advice;

import com.main.ra.exception.DatabaseException;
import com.main.ra.exception.UserInfoException;
import com.main.ra.model.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(UserInfoException.class)
    @ResponseBody
    public ResponseEntity handleUserInfoException(UserInfoException ex){
        return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseBody
    public ResponseEntity handlerDatabaseException(DatabaseException de){
        return new ResponseEntity<>(de.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
