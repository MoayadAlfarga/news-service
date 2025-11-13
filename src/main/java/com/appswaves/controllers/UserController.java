package com.appswaves.controllers;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;
import com.appswaves.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UsersService usersService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registrationUser(@RequestBody RegistrationUserDto registerUserDto) {
        return ResponseEntity.ok(usersService.registrationUser(registerUserDto));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(usersService.loginUser(loginRequestDto));
    }
}
