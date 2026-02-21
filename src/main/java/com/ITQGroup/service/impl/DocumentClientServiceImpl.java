package com.ITQGroup.service.impl;

import com.ITQGroup.client.DocumentClient;
import com.ITQGroup.dto.DocumentProcessingResultDto;
import com.ITQGroup.dto.DocumentResponseDto;
import com.ITQGroup.service.DocumentClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentClientServiceImpl implements DocumentClientService {

    private final DocumentClient documentClient;


    @Override
    public List<DocumentProcessingResultDto> sendToApproval(Long authorId, List<Long> ids) {

        return documentClient.sendApproval(authorId, ids);
    }

    @Override
    public DocumentResponseDto getDocumentById(Long id) {
        return documentClient.getById(id);
    }
}
