package com.ITQGroup.util;

import com.ITQGroup.constant.ExceptionConstant;
import com.ITQGroup.exception.NegativeQuantityFileException;
import com.ITQGroup.exception.FileQuantityParsingException;
import com.ITQGroup.io.DocumentFileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentBatchCreationUtil {

    private final DocumentFileReader fileReader;

    public Integer getDocsQuantityFromFile(){

        log.info("Entering getDocsQuantityFromFile() method");

        log.info("Reading documents quantity from file");

        String quantityAsString = fileReader.read();

        String trimmedString = quantityAsString.trim();

        try{

            log.info("Trying to parse string to integer");

            Integer docsQuantity = Integer.parseInt(trimmedString);

            log.info("Documents quantity from file equals: {}", docsQuantity);

            if (docsQuantity <= 0){

                log.error("Unacceptable documents quantity: {}", docsQuantity);

                throw new NegativeQuantityFileException(ExceptionConstant.NEGATIVE_FILE_QUANTITY + docsQuantity);
            }

            log.info("Exit getDocsQuantityFromFile() method");

            return docsQuantity;

        } catch (NumberFormatException e){

            log.error("Failed to convert data from file to Integer");

            throw new FileQuantityParsingException(ExceptionConstant.FAILED_TO_PARE_DOCUMENT_QUANTITY + quantityAsString);
        }
    }
}
