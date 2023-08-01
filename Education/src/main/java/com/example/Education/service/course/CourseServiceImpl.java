package com.example.Education.service.course;

import com.example.Education.dto.course.CourseResponse;
import com.example.Education.dto.course.CreateCourseRequest;
import com.example.Education.dto.course.UpdateCourseRequest;
import com.example.Education.dto.student.CreateStudentRequest;
import com.example.Education.exceptions.NotFoundException;
import com.example.Education.model.CourseEntity;
import com.example.Education.repository.CourseRepository;
import com.example.Education.validators.CompositeValidator;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Education.validators.CompositeValidator.hasValue;
import static com.example.Education.validators.CompositeValidator.joinViolations;

@Service
public class CourseServiceImpl implements  CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public CourseResponse save(CreateCourseRequest request) {
        preCreateValidation(request);
        CourseEntity courseEntity = courseRepository.save(mapToStudentEntity(request));
        return mapToStudentResponse(courseEntity);
    }

    @Override
    public CourseResponse update(UpdateCourseRequest request, Long id) {
        CourseEntity courseEntity = getCourseById(id);
        updateProps(request, courseEntity);
        return  mapToStudentResponse(courseRepository.save(courseEntity));
    }

    @Override
    public void delete(Long id) {
        CourseEntity courseEntity = getCourseById(id);
        courseRepository.delete(courseEntity);
    }

    @Override
    public CourseResponse retrieveById(Long id) {
        CourseEntity courseEntity = getCourseById(id);
        return mapToStudentResponse(courseEntity);
    }

    @Override
    public List<CourseResponse> retrieveAll() {
        return courseRepository.findAll().stream()
                .map(this::mapToStudentResponse).toList();
    }

    private CourseEntity getCourseById(Long id) {
        return courseRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Cannot Find Course By Id " + id));
    }

    private void updateProps(UpdateCourseRequest request, CourseEntity studentEntity) {
        studentEntity.setTitle(hasValue(request.getTitle()) ? request.getTitle() : studentEntity.getTitle());
        studentEntity.setDescription(hasValue(request.getDescription())? request.getDescription() : studentEntity.getDescription());
    }

    public void preCreateValidation(CreateCourseRequest createDto) {
        List<String> violations = new CompositeValidator<CreateCourseRequest, String>()
                .addValidator(r -> hasValue(r.getTitle()), "Title Cannot Be Empty")
                .addValidator(r -> hasValue(r.getDescription()), "Description Cannot Be Empty")
                .validate(createDto);
        joinViolations(violations);
    }

    private CourseEntity mapToStudentEntity(CreateCourseRequest request) {
        return CourseEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    private CourseResponse mapToStudentResponse(CourseEntity entity) {
        return CourseResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }
}
