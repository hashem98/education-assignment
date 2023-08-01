package com.example.Education.controller;

import com.example.Education.dto.AuthenticationResponse;
import com.example.Education.dto.CODE;
import com.example.Education.dto.CreateAuthenticationRequest;
import com.example.Education.dto.Response;
import com.example.Education.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v2/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthenticationResponse>> login(@RequestBody CreateAuthenticationRequest authenticationRequest) {

        return new ResponseEntity<>(Response.<AuthenticationResponse>builder()
                .data(authService.login(authenticationRequest))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build(), HttpStatus.OK);
    }

}
