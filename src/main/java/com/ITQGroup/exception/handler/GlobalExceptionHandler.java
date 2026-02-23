package com.ITQGroup.exception.handler;

import com.ITQGroup.dto.ErrorResponseDto;
import com.ITQGroup.enums.ErrorResponseCode;
import com.ITQGroup.exception.DocumentServiceBadRequestException;
import com.ITQGroup.exception.DocumentServiceEmptyResponseBodyException;
import com.ITQGroup.exception.DocumentServiceNotFoundException;
import com.ITQGroup.exception.DocumentServiceUnexpectedException;
import com.ITQGroup.exception.FileQuantityParsingException;
import com.ITQGroup.exception.QuantityFileReadingException;
import com.ITQGroup.exception.NegativeQuantityFileException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NegativeQuantityFileException.class)
    public ErrorResponseDto handleNegativeQuantityFileException(NegativeQuantityFileException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.NEGATIVE_FILE_QUANTITY.name(), LocalDateTime.now());
    }

    @ExceptionHandler(QuantityFileReadingException.class)
    public ErrorResponseDto handleQuantityFileReadingException(QuantityFileReadingException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.INTERNAL_SERVER_ERROR.name(), LocalDateTime.now());
    }

    @ExceptionHandler(FileQuantityParsingException.class)
    public ErrorResponseDto handleFileQuantityParsingException(FileQuantityParsingException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.INTERNAL_SERVER_ERROR.name(), LocalDateTime.now());
    }

    @ExceptionHandler(DocumentServiceBadRequestException.class)
    public ErrorResponseDto handleDocumentServiceBadRequestException(DocumentServiceBadRequestException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.BAD_REQUEST.name(), LocalDateTime.now());
    }

    @ExceptionHandler(DocumentServiceNotFoundException.class)
    public ErrorResponseDto handleDocumentServiceNotFoundException(DocumentServiceNotFoundException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.NOT_FOUND.name(), LocalDateTime.now());
    }

    @ExceptionHandler(DocumentServiceUnexpectedException.class)
    public ErrorResponseDto handleDocumentServiceUnexpectedException(DocumentServiceUnexpectedException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.INTERNAL_SERVER_ERROR.name(), LocalDateTime.now());
    }

    @ExceptionHandler(DocumentServiceEmptyResponseBodyException.class)
    public ErrorResponseDto handleDocumentServiceEmptyResponseBodyException(DocumentServiceEmptyResponseBodyException exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.EMPTY_RESPONSE_BODY.name(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleException(Exception exception) {

        return new ErrorResponseDto(exception.getMessage(), ErrorResponseCode.INTERNAL_SERVER_ERROR.name(), LocalDateTime.now());
    }


}
