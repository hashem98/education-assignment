package com.example.Education.controller;

import com.example.Education.dto.CODE;
import com.example.Education.dto.Response;
import com.example.Education.dto.course.CourseResponse;
import com.example.Education.dto.course.CreateCourseRequest;
import com.example.Education.dto.course.UpdateCourseRequest;
import com.example.Education.service.course.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping
    public ResponseEntity<Response<CourseResponse>> add(@RequestBody CreateCourseRequest request) {
        Response<CourseResponse> response = Response.<CourseResponse>builder().data(courseService.save(request))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CourseResponse>> update(@RequestBody UpdateCourseRequest request, @PathVariable Long id) {
        Response<CourseResponse> response = Response.<CourseResponse>builder().data(courseService.update(request, id))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(new Response<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CourseResponse>> getById(@PathVariable Long id) {
        Response<CourseResponse> response = Response.<CourseResponse>builder().data(courseService.retrieveById(id))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Response<List<CourseResponse>>> getAll() {
        Response<List<CourseResponse>> response = Response.<List<CourseResponse>>builder().data(courseService.retrieveAll())
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
