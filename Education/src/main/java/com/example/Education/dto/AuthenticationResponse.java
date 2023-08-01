package com.example.Education.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private Long userId;

    private String fullName;

    private String username;

    private String Email;

    private String role;

    private String token;

}
