package com.appswaves.service;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;

public interface UsersService {
    AuthenticationResponse registrationUser(RegistrationUserDto registrationUserDto);

    AuthenticationResponse loginUser(LoginRequestDto loginRequestDto);

    AuthenticationResponse registrationAdminUser(RegistrationUserDto registrationUserDto);

    AuthenticationResponse loginAdminUser(LoginRequestDto loginRequestDto);

    AuthenticationResponse registrationContentWriterUser(RegistrationUserDto registrationUserDto);

    AuthenticationResponse loginContentWriterUser(LoginRequestDto loginRequestDto);


}
