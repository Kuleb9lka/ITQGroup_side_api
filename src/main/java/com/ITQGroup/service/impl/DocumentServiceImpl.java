package com.ITQGroup.service.impl;

import com.ITQGroup.client.DocumentClient;
import com.ITQGroup.constant.Constant;
import com.ITQGroup.dto.DocumentBatchCreateRequestDto;
import com.ITQGroup.dto.DocumentShortResponseDto;
import com.ITQGroup.service.DocumentService;
import com.ITQGroup.util.DocumentBatchCreationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentBatchCreationUtil documentBatchCreationUtil;

    private final DocumentClient documentClient;

    @Override
    public List<DocumentShortResponseDto> fileBatchCreate() {

        Integer docsQuantityFromFile = documentBatchCreationUtil.readDocsQuantityFromFile();

        Long defaultSystemAuthorId = Constant.DEFAULT_SYSTEM_AUTHOR_ID;

        return documentClient.batchCreate(new DocumentBatchCreateRequestDto(defaultSystemAuthorId, docsQuantityFromFile));
    }
}
