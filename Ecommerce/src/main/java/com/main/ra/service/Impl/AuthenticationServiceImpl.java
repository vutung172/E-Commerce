package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.TokenDto;
import com.main.ra.model.dto.UserDetailAdapter;
import com.main.ra.model.dto.request.SignInRequest;
import com.main.ra.model.dto.request.SignUpRequest;
import com.main.ra.model.dto.response.SignInResponse;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.service.AuthenticationService;
import com.main.ra.validator.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private JwtTokenValidator tokenValidator;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder encoder;

    public boolean signUp(SignUpRequest req) {
        UserEntity userAdded = userService.addUser(req);
        if (userAdded != null) {
            return true;
        } else {
            throw new BaseException("exception.UserExisted",HttpStatus.NOT_FOUND);
        }
    }

    public SignInResponse signIn(SignInRequest req){
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUserName(),
                            req.getPassword()
                    )
            );
            UserDetailAdapter user = (UserDetailAdapter) auth.getPrincipal();
            String jwtToken = tokenValidator.generateToken(user);
            TokenDto token = new TokenDto("Bearer Token", jwtToken);
            return SignInResponse.builder()
                    .id(user.getUser().getId())
                    .fullName(user.getUser().getFullName())
                    .type(token.getType())
                    .accessToken(token.getToken())
                    .roles(user.getUser().getRoles().stream().map(r -> r.getRole().getRoleName()).collect(Collectors.toList()))
                    .build();

        } catch (RuntimeException re) {
            throw new BaseException("exception.database.UserOrPasswordIncorrect",HttpStatus.UNAUTHORIZED);
        }
    }


}
