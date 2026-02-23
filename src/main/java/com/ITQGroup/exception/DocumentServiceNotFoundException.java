package com.ITQGroup.exception;

public class DocumentServiceNotFoundException extends RuntimeException{
    public DocumentServiceNotFoundException(String message) {
        super(message);
    }
}
