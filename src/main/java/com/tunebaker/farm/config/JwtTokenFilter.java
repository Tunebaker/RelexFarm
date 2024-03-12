package com.tunebaker.farm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunebaker.farm.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final int JWT_START_POSITION = 7;
    private static final String AUTH_HEADER_START = "Bearer ";
    private final JwtTokenUtil jwtTokenUtil;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;
        String role = null;
        if (authHeader != null && authHeader.startsWith(AUTH_HEADER_START)) {
            log.info("Extracting JWT from request header...");
            jwtToken = authHeader.substring(JWT_START_POSITION);
            try {
                email = jwtTokenUtil.getUserEmail(jwtToken);
                role = jwtTokenUtil.getRole(jwtToken);
            } catch (Exception e) {
                ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.FORBIDDEN, e.getMessage()).build();
                log.info("Error during parsing JWT: {}", errorResponse);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String errorJson = objectMapper.writeValueAsString(errorResponse);
                response.getWriter().write(errorJson);
                response.flushBuffer();
                return;
            }
            log.info("Read from jwt: email={}, role={}", email, role);
        }

        if (email != null && role != null) {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(email, null, Set.of(new SimpleGrantedAuthority(role)));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
