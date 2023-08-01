package com.example.Education.service.auth;


import com.example.Education.dto.AuthenticationResponse;
import com.example.Education.dto.CreateAuthenticationRequest;

public interface AuthService {
    AuthenticationResponse login(CreateAuthenticationRequest authenticationRequest);

}
