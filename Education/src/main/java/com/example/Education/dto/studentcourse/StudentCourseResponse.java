package com.example.Education.dto.studentcourse;

import com.example.Education.dto.course.CourseResponse;
import com.example.Education.dto.student.StudentResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseResponse {
    private Long id ;
    private StudentResponse student ;
    private CourseResponse course ;
}
