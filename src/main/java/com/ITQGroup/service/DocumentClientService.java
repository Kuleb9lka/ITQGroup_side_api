package com.ITQGroup.service;

import com.ITQGroup.dto.DocumentProcessingResultDto;
import com.ITQGroup.dto.DocumentResponseDto;

import java.util.List;

public interface DocumentClientService {

    List<DocumentProcessingResultDto> sendToApproval(Long authorId, List<Long> ids);

    DocumentResponseDto getDocumentById(Long id);
}
