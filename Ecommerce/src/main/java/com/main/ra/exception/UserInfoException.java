package com.main.ra.exception;

import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoException extends RuntimeException{
    private MessageResponse errorMessage;
    private MessageLoader loader;

    public UserInfoException(String messageCode) {
        super();
        loader = new MessageLoader();
        errorMessage = new MessageResponse(loader.getMessage(messageCode));
    }
}
