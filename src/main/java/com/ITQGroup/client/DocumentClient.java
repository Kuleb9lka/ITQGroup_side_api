package com.ITQGroup.client;

import com.ITQGroup.dto.DocumentBatchCreateRequestDto;
import com.ITQGroup.dto.DocumentProcessingResultDto;
import com.ITQGroup.dto.DocumentResponseDto;
import com.ITQGroup.dto.DocumentShortResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "document-service", path = "/documents", url = "${api.documents.url}")
public interface DocumentClient {

    @GetMapping("/{id}")
    DocumentResponseDto getById(@PathVariable("id") Long id);


    @PostMapping("/batch-create")
    List<DocumentShortResponseDto> batchCreate(@RequestBody DocumentBatchCreateRequestDto dto);



    @PostMapping("/send-approval/{authorId}")
    List<DocumentProcessingResultDto> sendApproval(@PathVariable("authorId") Long authorId, @RequestBody List<Long> documentIds);


}
