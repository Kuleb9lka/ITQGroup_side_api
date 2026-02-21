package com.ITQGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentConcurrentResponseDto {


    private Integer threads;

    private Integer attemptsPerThread;

    private Integer expectedAttemptsCount;

    private Integer actualAttemptsCount;

    private Integer successAttempts;

    private Integer failedAttempts;

    private String finalDocumentStatus;

}
