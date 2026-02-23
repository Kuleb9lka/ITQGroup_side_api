package com.ITQGroup.io.impl;

import com.ITQGroup.constant.ExceptionConstant;
import com.ITQGroup.exception.DocumentQuantityFileReadingException;
import com.ITQGroup.io.DocumentFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DocumentFileReaderImpl implements DocumentFileReader {

    @Value(value = "${document-batch-creation.file.path}")
    private String path;


    @Override
    public String read() {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {

            String line = reader.readLine();

            if (line == null || line.trim().isEmpty()) {
                throw new DocumentQuantityFileReadingException(ExceptionConstant.EMPTY_QUANTITY_FILE);
            }

            return line.trim();

        } catch (IOException e) {
            throw new DocumentQuantityFileReadingException(ExceptionConstant.FAILED_TO_READ_FILE + path);
        }
    }
}
