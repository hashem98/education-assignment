package com.example.Education.service.course;

import com.example.Education.dto.course.CourseResponse;
import com.example.Education.dto.course.CreateCourseRequest;
import com.example.Education.dto.course.UpdateCourseRequest;
import com.example.Education.dto.student.CreateStudentRequest;
import com.example.Education.dto.student.StudentResponse;
import com.example.Education.dto.student.UpdateStudentRequest;

import java.util.List;

public interface CourseService {
    CourseResponse save (CreateCourseRequest request);

    CourseResponse update(UpdateCourseRequest request, Long id);

    void delete (Long id);

    CourseResponse retrieveById(Long id);

    List<CourseResponse> retrieveAll();
}
