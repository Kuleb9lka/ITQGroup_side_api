package com.ITQGroup.util;

import com.ITQGroup.constant.ExceptionConstant;
import com.ITQGroup.exception.DocumentPositiveQuantityException;
import com.ITQGroup.exception.DocumentQuantityFileReadingException;
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

                throw new DocumentPositiveQuantityException(ExceptionConstant.DOCUMENT_QUANTITY_ZERO_OR_LESS + docsQuantity);
            }

            return docsQuantity;

        } catch (NumberFormatException e){

            throw new DocumentQuantityFileReadingException(ExceptionConstant.FAILED_TO_PARE_DOCUMENT_QUANTITY + quantityAsString);
        }
    }
}
