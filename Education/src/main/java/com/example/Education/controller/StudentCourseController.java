package com.example.Education.controller;

import com.example.Education.dto.CODE;
import com.example.Education.dto.Response;
import com.example.Education.dto.student.StudentResponse;
import com.example.Education.dto.studentcourse.CreateStudentCourseRequest;
import com.example.Education.dto.studentcourse.StudentCourseResponse;
import com.example.Education.service.studentcourse.StudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher/students-courses")
public class StudentCourseController {
   private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Response<List<StudentCourseResponse>>> getAllByCourseId(@PathVariable Long courseId) {
        Response<List<StudentCourseResponse>> response = Response.<List<StudentCourseResponse>>builder()
                .data(studentCourseService.retrieveAllByCourseId(courseId))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Response<List<StudentCourseResponse>>> getAllBtStudentId(@PathVariable Long studentId) {
        Response<List<StudentCourseResponse>> response = Response.<List<StudentCourseResponse>>builder()
                .data(studentCourseService.retrieveAllByStudentId(studentId))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response<StudentCourseResponse>> assign(CreateStudentCourseRequest request) {
        Response<StudentCourseResponse> response = Response.<StudentCourseResponse>builder()
                .data(studentCourseService.assignCourseToStudent(request))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
