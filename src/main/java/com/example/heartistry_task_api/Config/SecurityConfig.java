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
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
                                "/wordsets/add",
                                "/wordsets/me/pagination",
                                "/wordsets/me/all",
                                "/wordsets/me/*",
                                "/wordsets/recommended/pagination",
                                "/wordsets/me/count",
                                "/wordsets",
                                "/words/add",
                                "/words/*/pagination",
                                "/words/*/all",
                                "/words/me/*",
                                "/words/me/count",
                                "/documents/add",
                                "/documents/me/pagination",
                                "/documents/me/all",
                                "/documents/me/*",
                                "/audit-logs/add",
                                "/audit-logs/me/statistics"
                        )
                        .authenticated()
                        .requestMatchers(
                                "/wordsets/*",
                                "/wordsets/all",
                                "/wordsets/all/pagination",
                                "/words/*",
                                "/words/all",
                                "/words/all/pagination",
                                "/documents/*",
                                "/documents/all",
                                "/documents/all/pagination",
                                "/audit-logs/all",
                                "/audit-logs/all/pagination"
                        )
                        .hasAuthority("admin") // Allow specific endpoint with specific role
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}