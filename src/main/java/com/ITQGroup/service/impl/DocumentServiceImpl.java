package com.ITQGroup.service.impl;

import com.ITQGroup.client.DocumentClient;
import com.ITQGroup.constant.Constant;
import com.ITQGroup.dto.DocumentBatchCreateRequestDto;
import com.ITQGroup.dto.DocumentShortResponseDto;
import com.ITQGroup.service.DocumentService;
import com.ITQGroup.util.DocumentBatchCreationUtil;
import com.ITQGroup.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentBatchCreationUtil documentBatchCreationUtil;

    private final DocumentClient documentClient;

    @Override
    public List<DocumentShortResponseDto> batchDocumentsCreation() {

        long start = System.nanoTime();

        log.info("Entering batchDocumentsCreation() method");

        log.info("Trying to get quantity from file");

        Integer docsQuantityFromFile = documentBatchCreationUtil.getDocsQuantityFromFile();

        log.info("{} documents will be created", docsQuantityFromFile);

        Long defaultSystemAuthorId = Constant.DEFAULT_SYSTEM_AUTHOR_ID;

        log.info("Sending create request");

        List<DocumentShortResponseDto> documentShortResponseDtos = documentClient.batchCreate(new DocumentBatchCreateRequestDto(defaultSystemAuthorId, docsQuantityFromFile));

        long end = System.nanoTime();

        log.info("Exit batchDocumentsCreation() method. Execution time: {}", TimeUtil.formatNanos(end - start));

        return documentShortResponseDtos;
    }
}
