package com.ITQGroup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeQuantityFileException extends RuntimeException{
    public NegativeQuantityFileException(String message) {
        super(message);
    }
}
