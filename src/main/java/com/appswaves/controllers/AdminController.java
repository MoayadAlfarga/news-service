package com.appswaves.controllers;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;
import com.appswaves.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private UsersService usersService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registrationAdminUser(@Valid  @RequestBody RegistrationUserDto registerAdminDto) {
        return ResponseEntity.ok(usersService.registrationAdminUser(registerAdminDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginAdminUser(@Valid @RequestBody LoginRequestDto authenticationRequest) {
        return ResponseEntity.ok(usersService.loginAdminUser(authenticationRequest));
    }
}
