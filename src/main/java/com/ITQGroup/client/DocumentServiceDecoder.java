package com.ITQGroup.client;

import com.ITQGroup.constant.ExceptionConstant;
import com.ITQGroup.dto.ExceptionResponseDto;
import com.ITQGroup.exception.DocumentServiceBadRequestException;
import com.ITQGroup.exception.DocumentServiceEmptyResponseBodyException;
import com.ITQGroup.exception.DocumentServiceNotFoundException;
import com.ITQGroup.exception.DocumentServiceUnexpectedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class DocumentServiceDecoder implements ErrorDecoder {

    private final ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.body() == null) {
            return new DocumentServiceEmptyResponseBodyException(
                    ExceptionConstant.EMPTY_BODY_RESPONSE + response.status());
        }

        try (InputStream inputStream = response.body().asInputStream()) {

            ExceptionResponseDto exceptionResponse = mapper.readValue(inputStream, ExceptionResponseDto.class);

            return switch (response.status()) {

                case 404 -> new DocumentServiceNotFoundException(exceptionResponse.getErrorMessage());

                case 400 -> new DocumentServiceBadRequestException(exceptionResponse.getErrorMessage());

                default ->
                        new DocumentServiceUnexpectedException("Unexpected status: " + response.status() + ", message: " + exceptionResponse.getErrorMessage());
            };

        } catch (IOException | IllegalArgumentException e) {

            return new DocumentServiceUnexpectedException(ExceptionConstant.FAILED_TO_DECODE_RESPONSE + response.status());
        }
    }
}
