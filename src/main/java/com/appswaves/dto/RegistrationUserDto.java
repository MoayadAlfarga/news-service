package com.appswaves.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationUserDto {
    private String fullName;
    private String email;
    private String password;
    private String dateOfBirthDate;
}
