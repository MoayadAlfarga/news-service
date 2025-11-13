package com.appswaves.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsernameNotFoundException extends RuntimeException {

    private String arabicMessage = "المستخدم غير موجود";
    private String englishMessage = "User not found";


    public UsernameNotFoundException(String message) {
        super(message);
    }
}
