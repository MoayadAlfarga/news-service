package com.appswaves.exceptions;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsNotFoundException extends RuntimeException {
    private String arabicMessage = "الخبر غير موجود";
    private String englishMessage = "News Not Found";

    public NewsNotFoundException(String message) {
        super(message);
    }
}
