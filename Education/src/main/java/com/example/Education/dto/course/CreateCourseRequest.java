package com.example.Education.dto.course;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCourseRequest {

    private String title;
    private String description;

}
