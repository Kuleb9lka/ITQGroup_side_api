package com.ITQGroup.api;

import com.ITQGroup.dto.DocumentConcurrentRequestDto;
import com.ITQGroup.dto.DocumentConcurrentResponseDto;
import com.ITQGroup.dto.DocumentShortResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Document API", description = "Operations for concurrent document processing and batch creation")
public interface DocumentApi {

    @Operation(summary = "Check document approving safety", description = "Runs concurrent approval attempts for a document.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Execution completed successfully", content = @Content(schema = @Schema(implementation = DocumentConcurrentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    DocumentConcurrentResponseDto checkDocumentApprovingSafety(@RequestBody @Valid DocumentConcurrentRequestDto dto);


    @Operation(summary = "Batch create documents", description = "Reads quantity from file and creates documents.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documents created successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = DocumentShortResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "Processing error")
    })
    @PostMapping("/batch-create")
    List<DocumentShortResponseDto> docsBatchCreate();
}
