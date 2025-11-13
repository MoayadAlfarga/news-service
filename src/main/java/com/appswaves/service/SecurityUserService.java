package com.appswaves.service;

import com.appswaves.dto.AuthenticationResponse;
import com.appswaves.dto.LoginRequestDto;
import com.appswaves.dto.RegistrationUserDto;
import com.appswaves.entity.UserEntity;
import com.appswaves.enums.Role;
import com.appswaves.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserService implements UsersService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtGenerationToken jwtGenerationToken;

    @Override
    public AuthenticationResponse registrationUser(RegistrationUserDto registrationUserDto) {
        if (userRepository.existsByEmail(registrationUserDto.getEmail())) {
//            throw new AlreadyExists("User Account Bank  Is Already Exists !! :" + registrationUserDto.getEmail());
            throw new RuntimeException("User Account Bank  Is Already Exists !! :" + registrationUserDto.getEmail());
            // exception

        }
        UserEntity user = UserEntity.builder()
                .fullName(registrationUserDto.getFullName())
                .email(registrationUserDto.getEmail())
                .DateOfBirthDate(registrationUserDto.getDateOfBirthDate())
                .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
        // add  exception
    }
    @Override
    public AuthenticationResponse loginUser(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),
                loginRequestDto.getPassword()));
        var user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
