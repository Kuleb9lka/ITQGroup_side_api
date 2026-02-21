package com.ITQGroup.service;

import com.ITQGroup.dto.DocumentConcurrentResponseDto;

public interface RequestRunnerService {

    DocumentConcurrentResponseDto run(Integer threads, Integer attempts, Long authorId, Long documentId);
}
