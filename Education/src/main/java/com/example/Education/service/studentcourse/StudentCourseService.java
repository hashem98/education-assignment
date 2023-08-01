package com.example.Education.service.studentcourse;

import com.example.Education.dto.studentcourse.CreateStudentCourseRequest;
import com.example.Education.dto.studentcourse.StudentCourseResponse;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourseResponse> retrieveAllByCourseId(Long courseId);
    List<StudentCourseResponse> retrieveAllByStudentId(Long studentId);

    StudentCourseResponse assignCourseToStudent(CreateStudentCourseRequest request);
}
