package com.example.Education.service.student;

import com.example.Education.dto.student.CreateStudentRequest;
import com.example.Education.dto.student.StudentResponse;
import com.example.Education.dto.student.UpdateStudentRequest;

import java.util.List;

public interface StudentService {
    StudentResponse save (CreateStudentRequest request);

    StudentResponse update(UpdateStudentRequest request, Long id);

    void delete (Long id);

    StudentResponse retrieveById(Long id);

    List<StudentResponse> retrieveAllStudents();
}
