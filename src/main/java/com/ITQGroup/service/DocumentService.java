package com.ITQGroup.service;

import com.ITQGroup.dto.DocumentShortResponseDto;

import java.util.List;

public interface DocumentService {

    List<DocumentShortResponseDto> batchDocumentsCreation();
}
