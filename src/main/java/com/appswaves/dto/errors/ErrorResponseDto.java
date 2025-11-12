package com.appswaves.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDto {
    private  String arabicMessage;
    private String englishMessage;
    private LocalDateTime errorTime;
}
