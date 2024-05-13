package com.main.ra.config;

import com.main.ra.exception.JwtFilterException;
import com.main.ra.model.dto.UserDetailAdapter;
import com.main.ra.service.Impl.UserServiceImpl;
import com.main.ra.validator.JwtTokenValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer")) {
                String jwtToken = authHeader.substring(7);
                if (tokenValidator.isJwtToken(jwtToken)) {
                    String userName = tokenValidator.getUserName(jwtToken);
                    UserDetailAdapter userDetails = userService
                            .loadUserByUsername(userName);
                    userDetails.setNonExpiredStatus(tokenValidator.isNonExpiredToken(jwtToken));
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

                } else {
                    throw new JwtFilterException("exception.authentication.TokenExpired",HttpStatus.UNAUTHORIZED);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtFilterException je) {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(je.getStatus().value());
            response.getWriter().write(je.getErrorMessage().getMessage());
        } catch (Exception e){
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(e.getMessage());
        }

    }
}
