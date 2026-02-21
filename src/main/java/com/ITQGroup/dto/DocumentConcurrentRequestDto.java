package com.ITQGroup.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentConcurrentRequestDto {

    @Min(value = 1, message = "Attempts can't be less then 1")
    @NotNull(message = "Attempts can't be null")
    private Integer attempts;

    @Min(value = 1, message = "Threads can't be less then 1")
    @NotNull(message = "Threads can't be null")
    private Integer threads;

    @Min(value = 1, message = "Author ID can't be less then 1")
    @NotNull(message = "Author ID can't be null")
    private Long authorId;

    @Min(value = 1, message = "Document ID can't be less then 1")
    @NotNull(message = "Document ID can't be null")
    private Long documentId;

}
