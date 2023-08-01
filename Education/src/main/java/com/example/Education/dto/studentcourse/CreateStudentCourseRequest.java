package com.example.Education.dto.studentcourse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStudentCourseRequest {
    private Long studentId;
    private Long courseId;
}
