package com.example.heartistry_task_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.heartistry_task_api.ConfigService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtFilter implements Filter {
    @Autowired
    private ConfigService configService = new ConfigService();

    private final String SECRET_KEY = "c66077ac1510b83fbda3822b8007fafb9fdf6e550d13574bfd98164d0328261e"; // Same as used in NestJS

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Extracting fields from the payload
                Integer userId = claims.get("id", Integer.class); // Extract `id`
                String username = claims.get("username", String.class); // Extract `username`
                String role = claims.get("role", String.class); // Extract `role`

                // Adding extracted information to the request for further use
                httpRequest.setAttribute("userId", userId);
                httpRequest.setAttribute("username", username);
                httpRequest.setAttribute("role", role);

            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }
}
