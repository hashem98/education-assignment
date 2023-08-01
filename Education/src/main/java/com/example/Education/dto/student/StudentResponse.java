package com.example.Education.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class StudentResponse {
    private Long id;
    private String englishFullName;
    private String arabicFullName;
    private String email;
    private String telephoneNumber;
    private String address;
}
