package com.appswaves.service;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;

public interface AdminService {


    AuthenticationResponse registrationAdminUser(RegistrationUserDto registrationUserDto);

    AuthenticationResponse loginAdminUser(LoginRequestDto loginRequestDto);



}
