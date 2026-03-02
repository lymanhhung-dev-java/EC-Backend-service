package com.example.backend_service.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backend_service.commom.TokenType;
import com.example.backend_service.service.auth.JwtService;
import com.example.backend_service.service.auth.UserServiceDetail;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomizeRequestFiter extends OncePerRequestFilter {
     @Autowired
    private JwtService jwtService;
    @Autowired
    private UserServiceDetail userServiceDetail;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());

        var authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.substring(7);
            log.info("Authorization token present: {}", token);

            try {
                var username = jwtService.extractUsername(token, TokenType.ACCESS_TOKEN);
                log.info("Extracted username from token: {}", username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    
                    var roles = jwtService.extractRoles(token, TokenType.ACCESS_TOKEN);
                    var authorities = roles != null ? roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()) : java.util.Collections.<org.springframework.security.core.GrantedAuthority>emptyList();

                    var authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);
                    
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    var securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authenticationToken);
                    
                    SecurityContextHolder.setContext(securityContext);
                }
                
            } catch (Exception e) {
                log.error("Token invalid or expired: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
