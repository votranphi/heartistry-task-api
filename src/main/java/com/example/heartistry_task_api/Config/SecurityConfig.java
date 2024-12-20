package com.example.heartistry_task_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.heartistry_task_api.JwtVerification.JwtFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/public/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/actuator/**")
                        .permitAll() // Allow necessary endpoints
                        .requestMatchers(
                                "/wordsets/all",
                                "/words/all",
                                "/documents/all")
                        .hasAuthority("admin") // Allow specific endpoint with specific role
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}