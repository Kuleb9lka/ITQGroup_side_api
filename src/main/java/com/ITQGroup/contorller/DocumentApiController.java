package com.ITQGroup.contorller;

import com.ITQGroup.dto.DocumentConcurrentRequestDto;
import com.ITQGroup.dto.DocumentConcurrentResponseDto;
import com.ITQGroup.dto.DocumentShortResponseDto;
import com.ITQGroup.service.DocumentService;
import com.ITQGroup.service.RequestRunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents-api")
@RequiredArgsConstructor
public class DocumentApiController {

    private final RequestRunnerService requestRunnerService;

    private final DocumentService documentService;

    @PostMapping
    public DocumentConcurrentResponseDto checkDocumentApprovingSafety(@RequestBody DocumentConcurrentRequestDto dto) {

        return requestRunnerService.run(dto.getThreads(), dto.getAttempts(), dto.getAuthorId(), dto.getDocumentId());
    }

    @PostMapping("/batch-create")
    public List<DocumentShortResponseDto> docsBatchCreate(){
        return documentService.fileBatchCreate();
    }
}
