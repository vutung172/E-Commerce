package com.main.ra.config;

import com.main.ra.service.Impl.UserServiceImpl;
import com.main.ra.validator.JwtTokenValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilterConfig extends OncePerRequestFilter {
    @Autowired
    private JwtTokenValidator tokenValidator;
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String jwtToken = authHeader.substring(7);
            if (tokenValidator.validateToken(jwtToken)) {
                String userName = tokenValidator.getUserName(jwtToken);
                UserDetails userDetails = userService
                        .loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken
                        .setDetails(new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
