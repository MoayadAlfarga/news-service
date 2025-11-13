package com.appswaves.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataRequiredException extends RuntimeException {
    private String arabicMessage = "Data Null Or Empty ";
    private String englishMessage = "احد الحقول الخاصة بك فارغة";


    public DataRequiredException(String message) {
        super(message);
    }
}
