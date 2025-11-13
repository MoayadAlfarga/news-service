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
@RequestMapping("/content")
@AllArgsConstructor
public class ContentWriterController {
    private UsersService usersService;

    @PostMapping("/writer-register")
    public ResponseEntity<AuthenticationResponse> registrationContentWriterUser(@RequestBody RegistrationUserDto registerAdminDto) {
        return ResponseEntity.ok(usersService.registrationContentWriterUser(registerAdminDto));
    }

    @PostMapping("/writer-login")
    public ResponseEntity<AuthenticationResponse> loginContentWriterUser(@RequestBody LoginRequestDto authenticationRequest) {
        return ResponseEntity.ok(usersService.loginContentWriterUser(authenticationRequest));
    }
}
