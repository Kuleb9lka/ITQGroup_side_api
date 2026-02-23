package com.ITQGroup.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocumentServiceNotFoundException extends RuntimeException{
    public DocumentServiceNotFoundException(String message) {
        super(message);
    }
}
