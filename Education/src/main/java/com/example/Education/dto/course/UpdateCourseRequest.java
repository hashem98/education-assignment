package com.example.Education.dto.course;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCourseRequest {

    private String title;
    private String description;

}
