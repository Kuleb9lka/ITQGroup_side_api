package com.ITQGroup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DocumentServiceUnexpectedException extends RuntimeException {
    public DocumentServiceUnexpectedException(String message) {
        super(message);
    }
}
