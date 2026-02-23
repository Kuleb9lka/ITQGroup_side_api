package com.ITQGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentServiceExceptionResponseDto {

    private String errorStatus;

    private String errorMessage;
}
