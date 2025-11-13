package com.appswaves.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnexpectedErrorException extends RuntimeException {
    private String arabicMessage = "حدث خطأ غير متوقع";
    private String englishMessage = "Unexpected Error";


    public UnexpectedErrorException(String message) {
        super(message);
    }
}
