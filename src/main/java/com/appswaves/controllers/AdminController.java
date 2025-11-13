package com.appswaves.controllers;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;
import com.appswaves.service.AdminService;
import com.appswaves.service.UsersService;
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
    private AdminService adminService;

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegistrationUserDto registerAdminDto) {
        return ResponseEntity.ok(adminService.registrationAdminUser(registerAdminDto));
    }

    @PostMapping("/login-admin")
    public ResponseEntity<AuthenticationResponse> loginAdmin(@RequestBody LoginRequestDto authenticationRequest) {
        return ResponseEntity.ok(adminService.loginAdminUser(authenticationRequest));
    }
}
