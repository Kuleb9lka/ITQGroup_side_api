package com.ITQGroup.service.impl;

import com.ITQGroup.constant.Constant;
import com.ITQGroup.dto.DocumentConcurrentResponseDto;
import com.ITQGroup.dto.DocumentProcessingResultDto;
import com.ITQGroup.dto.DocumentResponseDto;
import com.ITQGroup.service.DocumentClientService;
import com.ITQGroup.service.RequestRunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RequestRunnerServiceImpl implements RequestRunnerService {

    private final DocumentClientService documentClientService;


    @Override
    public DocumentConcurrentResponseDto run(Integer threads, Integer attempts, Long authorId, Long documentId) {

        List<DocumentProcessingResultDto> documentProcessingResultDtos;

        try {
            documentProcessingResultDtos = collectMultithreadingResults(threads, attempts, authorId, documentId);

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return calculateResults(threads, attempts, documentId, documentProcessingResultDtos);
    }

    private List<DocumentProcessingResultDto> collectMultithreadingResults(Integer threads, Integer attempts, Long authorId, Long documentId) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch startLatch = new CountDownLatch(1);

        List<Future<List<DocumentProcessingResultDto>>> futures = IntStream.range(0, threads)
                .<Future<List<DocumentProcessingResultDto>>>mapToObj(t -> executor.submit(() -> {
                    startLatch.await();
                    List<DocumentProcessingResultDto> results = new ArrayList<>();
                    for (int i = 0; i < attempts; i++) {
                        results.add(doRequest(authorId, documentId));
                    }
                    return results;
                }))
                .toList();

        startLatch.countDown();

        List<DocumentProcessingResultDto> allResults = futures.stream()
                .flatMap(f -> {
                    try {
                        return f.get().stream();
                    } catch (Exception e) {
                        return Stream.empty();
                    }
                })
                .toList();

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        return allResults;
    }


    private DocumentConcurrentResponseDto calculateResults(Integer threads, Integer attempts, Long documentId, List<DocumentProcessingResultDto> resultDtos) {

        DocumentResponseDto documentById = getDocumentById(documentId);
        String finalStatus = documentById.getStatus();

        int resultsSize = resultDtos.size();

        int successResultsCount = 0;

        int failedAttempts = 0;

        for (DocumentProcessingResultDto dto : resultDtos) {

            if (Constant.SUCCESS_RESPONSE_STATUS.equals(dto.getStatus())) {

                successResultsCount++;
            } else {

                failedAttempts++;
            }

        }

        Integer expectedAttemptsCount = threads * attempts;

        return new DocumentConcurrentResponseDto(threads, attempts, expectedAttemptsCount, resultsSize, successResultsCount, failedAttempts, finalStatus);
    }

    private DocumentResponseDto getDocumentById(Long id) {

        return documentClientService.getDocumentById(id);
    }

    private DocumentProcessingResultDto doRequest(Long authorId, Long documentId) {

        return documentClientService.sendToApproval(authorId, List.of(documentId)).get(0);
    }

}
