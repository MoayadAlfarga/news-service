package com.appswaves.config;

import com.appswaves.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers(
                                        "/users/register",
                                        "/users/login",
                                        "/content/writer-register",
                                        "/content/writer-login",
                                        "/admin/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/news/read", "/news/read-all")
                        .hasAnyAuthority("USER", "CONTENT_WRITER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/news/add")
                        .hasAnyAuthority("CONTENT_WRITER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/news/read-admin")
                        .hasAnyAuthority("CONTENT_WRITER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/news/update")
                        .hasAnyAuthority("CONTENT_WRITER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/news/remove")
                        .hasAnyAuthority("CONTENT_WRITER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
