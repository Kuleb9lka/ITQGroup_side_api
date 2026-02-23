package com.ITQGroup.util;

import com.ITQGroup.constant.ExceptionConstant;
import com.ITQGroup.exception.NegativeQuantityFileException;
import com.ITQGroup.exception.FileQuantityParsingException;
import com.ITQGroup.io.DocumentFileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentBatchCreationUtil {

    private final DocumentFileReader fileReader;

    public Integer readDocsQuantityFromFile(){

        String quantityAsString = fileReader.read();

        String trimmedString = quantityAsString.trim();

        try{

            Integer docsQuantity = Integer.parseInt(trimmedString);

            if (docsQuantity <= 0){

                throw new NegativeQuantityFileException(ExceptionConstant.NEGATIVE_FILE_QUANTITY + docsQuantity);
            }

            return docsQuantity;

        } catch (NumberFormatException e){

            throw new FileQuantityParsingException(ExceptionConstant.FAILED_TO_PARE_DOCUMENT_QUANTITY + quantityAsString);
        }
    }
}
