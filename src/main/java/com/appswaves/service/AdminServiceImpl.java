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
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtGenerationToken jwtGenerationToken;

    @Override
    public AuthenticationResponse registrationAdminUser(RegistrationUserDto registrationUserDto) {
        if (userRepository.existsByEmail(registrationUserDto.getEmail())) {
            throw new AlreadyExistsException();
        }
        UserEntity user = UserEntity.builder()
                .fullName(registrationUserDto.getFullName())
                .email(registrationUserDto.getEmail())
                .DateOfBirthDate(registrationUserDto.getDateOfBirthDate())
                .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse loginAdminUser(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),
                loginRequestDto.getPassword()));
        var user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
