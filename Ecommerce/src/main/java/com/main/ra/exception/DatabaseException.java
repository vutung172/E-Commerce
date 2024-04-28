package com.main.ra.exception;

import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.util.MessageLoader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.orm.jpa.JpaSystemException;

@Getter
@Setter
public class DatabaseException extends JpaSystemException {
    private MessageResponse errorMessage;
    private MessageLoader loader;

    public DatabaseException(RuntimeException ex, String errorCode) {
        super(ex);
        loader = new MessageLoader();
        errorMessage = new MessageResponse(loader.getMessage(errorCode));
    }
}
