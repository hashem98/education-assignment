package com.example.Education.controller;

import com.example.Education.dto.CODE;
import com.example.Education.dto.Response;
import com.example.Education.dto.student.CreateStudentRequest;
import com.example.Education.dto.student.StudentResponse;
import com.example.Education.dto.student.UpdateStudentRequest;
import com.example.Education.service.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Response<StudentResponse>> add(@RequestBody CreateStudentRequest request) {
        Response<StudentResponse> response = Response.<StudentResponse>builder().data(studentService.save(request))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<StudentResponse>> update(@RequestBody UpdateStudentRequest request, @PathVariable Long id) {
        Response<StudentResponse> response = Response.<StudentResponse>builder().data(studentService.update(request, id))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(new Response<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<StudentResponse>> getById(@PathVariable Long id) {
        Response<StudentResponse> response = Response.<StudentResponse>builder().data(studentService.retrieveById(id))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Response<List<StudentResponse>>> getAll() {
        Response<List<StudentResponse>> response = Response.<List<StudentResponse>>builder().data(studentService.retrieveAllStudents())
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
