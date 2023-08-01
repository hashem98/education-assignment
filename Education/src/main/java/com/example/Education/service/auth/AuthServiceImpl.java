package com.example.Education.service.auth;

import com.example.Education.dto.AuthenticationResponse;
import com.example.Education.dto.CreateAuthenticationRequest;
import com.example.Education.dto.CustomUserDetails;
import com.example.Education.exceptions.NotFoundException;
import com.example.Education.exceptions.UserNotFoundException;
import com.example.Education.model.UserEntity;
import com.example.Education.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           CustomUserDetailsService userDetailsService,
                           JWTUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticationResponse login(CreateAuthenticationRequest authenticationRequest) {
        requiredNonNUll(authenticationRequest.getUsername(), "username");
        requiredNonNUll(authenticationRequest.getPassword(), "password");

        String username = authenticationRequest.getUsername().toLowerCase();
        String password = authenticationRequest.getPassword();

        authenticate(username, password);
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);
        return AuthenticationResponse.builder()
                .userId(userDetails.getId())
                .username(userDetails.getUsername())
                .fullName(userDetails.getFullName())
                .Email(userDetails.getEmail())
                .role(userDetails.getRole())
                .token("Bearer " + accessToken)
                .build();
    }


    private UserEntity getUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No User Found with User ID: {" + userId + "}"));
    }


    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationServiceException e) {
            throw new UserNotFoundException("Incorrect Username");
        }
    }

    private void requiredNonNUll(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()) {
            throw new NullPointerException(name + " can't be empty!");
        }
    }
}
