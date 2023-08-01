package com.example.Education.dto.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStudentRequest {

    private String englishFullName;
    private String arabicFullName;
    private String email;
    private String telephoneNumber;
    private String address;
}
