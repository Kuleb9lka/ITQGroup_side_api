package com.ITQGroup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class DocumentServiceEmptyResponseBodyException extends RuntimeException{
    public DocumentServiceEmptyResponseBodyException(String message) {
        super(message);
    }
}
