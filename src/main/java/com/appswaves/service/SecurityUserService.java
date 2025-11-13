package com.appswaves.service;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;
import com.appswaves.entity.UserEntity;
import com.appswaves.enums.Role;
import com.appswaves.exceptions.AlreadyExistsException;
import com.appswaves.repository.UserRepository;
import com.appswaves.security.JwtGenerationToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SecurityUserService implements UsersService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtGenerationToken jwtGenerationToken;

    public UserEntity buildUserInformation(RegistrationUserDto registrationUserDto, Role role) {
        return UserEntity.builder()
                .fullName(registrationUserDto.getFullName())
                .email(registrationUserDto.getEmail())
                .DateOfBirthDate(registrationUserDto.getDateOfBirthDate())
                .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                .role(role)
                .build();
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
    private AuthenticationResponse registrationProcessWithRole(RegistrationUserDto registrationUserDto, Role role) {
        this.checkIfUserExists(registrationUserDto);
        UserEntity user = buildUserInformation(registrationUserDto, role);
        saveUser(user);
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private AuthenticationResponse loginProcess(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),
                loginRequestDto.getPassword()));
        var user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private void checkIfUserExists(RegistrationUserDto registrationUserDto) {
        if (userRepository.existsByEmail(registrationUserDto.getEmail())) {
            throw new AlreadyExistsException();
        }
    }
    @Override
    public AuthenticationResponse registrationUser(RegistrationUserDto registrationUserDto) {
        checkIfUserExists(registrationUserDto);
        return registrationProcessWithRole(registrationUserDto, Role.USER);
    }

    @Override
    public AuthenticationResponse registrationAdminUser(RegistrationUserDto registrationUserDto) {
        checkIfUserExists(registrationUserDto);
        return this.registrationProcessWithRole(registrationUserDto, Role.ADMIN);
    }

    @Override
    public AuthenticationResponse registrationContentWriterUser(RegistrationUserDto registrationUserDto) {
        checkIfUserExists(registrationUserDto);
        return this.registrationProcessWithRole(registrationUserDto, Role.CONTENT_WRITER);
    }

    @Override
    public AuthenticationResponse loginAdminUser(LoginRequestDto loginRequestDto) {
        return this.loginProcess(loginRequestDto);
    }

    @Override
    public AuthenticationResponse loginContentWriterUser(LoginRequestDto loginRequestDto) {
        return this.loginAdminUser(loginRequestDto);
    }

    @Override
    public AuthenticationResponse loginUser(LoginRequestDto loginRequestDto) {
        return loginProcess(loginRequestDto);
    }
}
