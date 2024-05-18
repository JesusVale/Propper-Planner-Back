package com.propperplanner.propperplanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OverlapException extends RuntimeException{

    public OverlapException() {
    }

    public OverlapException(String message) {
        super(message);
    }

    public OverlapException(String message, Throwable cause) {
        super(message, cause);
    }
}
