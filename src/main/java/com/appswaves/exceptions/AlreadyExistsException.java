package com.appswaves.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlreadyExistsException extends RuntimeException {

    private String arabicMessage = "The User Already Exists";
    private String englishMessage = "المستخدم موجود بالفعل";

    public AlreadyExistsException(String message) {
        super(message);
    }
}
