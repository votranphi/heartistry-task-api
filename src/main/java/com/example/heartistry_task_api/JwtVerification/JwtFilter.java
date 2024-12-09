package com.example.heartistry_task_api.JwtVerification;

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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.heartistry_task_api.ConfigService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtFilter implements Filter {
    @Autowired
    private ConfigService configService = new ConfigService();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(configService.getJwtSecret().getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Extracting fields from the payload
                Integer id = claims.get("id", Integer.class);
                String username = claims.get("username", String.class);
                String role = claims.get("role", String.class);

                // Adding extracted information to the request for further use
                httpServletRequest.setAttribute("id", id);
                httpServletRequest.setAttribute("username", username);
                httpServletRequest.setAttribute("role", role);

                // Adding authorities for Spring Security
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                // This could be extended for user-based permissions
                SecurityContextHolder.getContext().setAuthentication(
                        new JwtAuthenticationToken(id, token, authorities)
                );
            } catch (Exception e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
