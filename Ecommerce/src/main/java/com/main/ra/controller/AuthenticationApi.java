package com.main.ra.controller;

import com.main.ra.exception.UserInfoException;
import com.main.ra.model.dto.request.SignInRequest;
import com.main.ra.model.dto.request.SignUpRequest;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.dto.response.SignInResponse;
import com.main.ra.service.Impl.AuthenticationServiceImpl;
import com.main.ra.util.MessageLoader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.myservice.com/v1/auth")
@Validated
public class AuthenticationApi {
    @Autowired
    private AuthenticationServiceImpl autService;
    @Autowired
    private MessageLoader loader;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequest signUp) throws UserInfoException {
        autService.signUp(signUp);
        MessageResponse message = new MessageResponse(loader.getMessage("register.success"));
        return ResponseEntity.ok(message);

    }

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody @Valid SignInRequest req) throws UserInfoException {
        SignInResponse resp = autService.signIn(req);
        return ResponseEntity.ok(resp);
    }
}
