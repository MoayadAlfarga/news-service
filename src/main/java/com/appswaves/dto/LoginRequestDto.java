package com.appswaves.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDto {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;
}
