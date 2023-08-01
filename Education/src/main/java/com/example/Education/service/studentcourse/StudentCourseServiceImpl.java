package com.example.Education.service.studentcourse;

import com.example.Education.dto.course.CourseResponse;
import com.example.Education.dto.student.StudentResponse;
import com.example.Education.dto.studentcourse.CreateStudentCourseRequest;
import com.example.Education.dto.studentcourse.StudentCourseResponse;
import com.example.Education.model.CourseEntity;
import com.example.Education.model.StudentCourseEntity;
import com.example.Education.model.StudentEntity;
import com.example.Education.repository.CourseRepository;
import com.example.Education.repository.StudentCourseRepository;
import com.example.Education.repository.StudentRepository;
import com.example.Education.validators.CompositeValidator;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Education.validators.CompositeValidator.joinViolations;
import static java.util.Objects.nonNull;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentCourseResponse> retrieveAllByCourseId(Long courseId) {
        return studentCourseRepository.findAllByCourseId(courseId).stream()
                .map(this::mapToStudentCourseResponse).toList();
    }

    @Override
    public List<StudentCourseResponse> retrieveAllByStudentId(Long studentId) {
        return studentCourseRepository.findAllByStudentId(studentId).stream()
                .map(this::mapToStudentCourseResponse).toList();
    }

    @Override
    public StudentCourseResponse assignCourseToStudent(CreateStudentCourseRequest request) {
        preAssignValidation(request);
        StudentCourseEntity studentCourseEntity = StudentCourseEntity.builder()
                .student(StudentEntity.builder().id(request.getStudentId()).build())
                .course(CourseEntity.builder().id(request.getCourseId()).build())
                .build();
        return mapToStudentCourseResponse(studentCourseRepository.save(studentCourseEntity));
    }

    private void preAssignValidation(CreateStudentCourseRequest request) {
        List<String> violations = new CompositeValidator<CreateStudentCourseRequest, String>()
                .addValidator(r -> nonNull(r.getStudentId()), "Student Id Cannot Be Empty")
                .addValidator(r -> nonNull(r.getCourseId()), "Course Id  Cannot Be Empty")
                .addValidator(r -> studentCourseRepository.findOne(Example.of(StudentCourseEntity.builder()
                                .student(StudentEntity.builder().id(request.getStudentId()).build())
                                .course(CourseEntity.builder().id(request.getCourseId()).build()).build())).isEmpty()
                        , "course with id " + request.getCourseId() + "already assigned to student with id " + request.getStudentId())
                .addValidator(r -> nonNull(studentRepository.findById(request.getStudentId()).orElse(null)), "student not found")
                .addValidator(r -> nonNull(courseRepository.findById(request.getCourseId()).orElse(null)), "course not found")
                .validate(request);
        joinViolations(violations);

    }

    StudentCourseResponse mapToStudentCourseResponse(StudentCourseEntity studentCourseEntity) {
        return StudentCourseResponse.builder().id(studentCourseEntity.getId())
                .student(StudentResponse.builder()
                        .arabicFullName(studentCourseEntity.getStudent().getArabicFullName())
                        .englishFullName(studentCourseEntity.getStudent().getEnglishFullName())
                        .email(studentCourseEntity.getStudent().getEmail())
                        .telephoneNumber(studentCourseEntity.getStudent().getTelephoneNumber())
                        .address(studentCourseEntity.getStudent().getAddress())
                        .build())
                .course(CourseResponse.builder()
                        .title(studentCourseEntity.getCourse().getTitle())
                        .description(studentCourseEntity.getCourse().getDescription())
                        .build()).build();
    }
}
