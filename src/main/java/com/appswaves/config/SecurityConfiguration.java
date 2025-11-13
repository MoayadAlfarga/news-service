package com.appswaves.config;

import com.appswaves.service.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private AuthenticationProvider authenticationProvider;
    private JwtAuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request ->

                        request
                                .requestMatchers("/admin/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/users/retrieve-all").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "users/retrieve-user/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "users/update/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "users/remove/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/users/**").permitAll()
//                                .requestMatchers("/account/**").permitAll()
//                                .requestMatchers("/info/**").permitAll()
//                                      .requestMatchers("/admin/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "accounts/retrieve-all").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "accounts/retrieve-account/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "accounts/update/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "accounts/remove/{userId}").hasAnyAuthority("ADMIN")
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
