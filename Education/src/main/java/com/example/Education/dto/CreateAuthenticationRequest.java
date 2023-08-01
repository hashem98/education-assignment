package com.example.Education.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
public class CreateAuthenticationRequest {

    private String username;

    private String password;
}
