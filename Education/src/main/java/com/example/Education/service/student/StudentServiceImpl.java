package com.example.Education.service.student;

import com.example.Education.dto.student.CreateStudentRequest;
import com.example.Education.dto.student.StudentResponse;
import com.example.Education.dto.student.UpdateStudentRequest;
import com.example.Education.exceptions.NotFoundException;
import com.example.Education.model.StudentEntity;
import com.example.Education.repository.StudentRepository;
import com.example.Education.validators.CompositeValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.Education.validators.CompositeValidator.hasValue;
import static com.example.Education.validators.CompositeValidator.joinViolations;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse save(CreateStudentRequest request) {
        preCreateValidation(request);
        StudentEntity studentEntity = studentRepository.save(mapToStudentEntity(request));
        return mapToStudentResponse(studentEntity);
    }

    @Override
    public StudentResponse update(UpdateStudentRequest request, Long id) {
        StudentEntity studentEntity = getStudentById(id);
        updateProps(request, studentEntity);
        return  mapToStudentResponse(studentRepository.save(studentEntity));
    }

    @Override
    public void delete(Long id) {
        StudentEntity studentEntity = getStudentById(id);
        studentRepository.delete(studentEntity);
    }

    @Override
    public StudentResponse retrieveById(Long id) {
        StudentEntity studentEntity = getStudentById(id);
        return mapToStudentResponse(studentEntity);
    }

    @Override
    public List<StudentResponse> retrieveAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToStudentResponse).toList();
    }

    private StudentEntity getStudentById(Long id) {
        return studentRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Cannot Find Student By Id " + id));
    }

    private void updateProps(UpdateStudentRequest request, StudentEntity studentEntity) {
        studentEntity.setArabicFullName(hasValue(request.getArabicFullName()) ? request.getArabicFullName() : studentEntity.getArabicFullName());
        studentEntity.setEnglishFullName(hasValue(request.getEnglishFullName())? request.getEnglishFullName() : studentEntity.getEnglishFullName());
        studentEntity.setEmail(hasValue(request.getEmail())? request.getEmail() : studentEntity.getEmail());
        studentEntity.setTelephoneNumber(hasValue(request.getTelephoneNumber())? request.getTelephoneNumber() : studentEntity.getTelephoneNumber());
        studentEntity.setAddress(hasValue(request.getAddress())? request.getAddress() : studentEntity.getAddress());
    }

    public void preCreateValidation(CreateStudentRequest createDto) {
        List<String> violations = new CompositeValidator<CreateStudentRequest, String>()
                .addValidator(r -> hasValue(r.getArabicFullName()), "Arabic Full Name Cannot Be Empty")
                .addValidator(r -> hasValue(r.getEnglishFullName()), "English Full Name Cannot Be Empty")
                .addValidator(r -> hasValue(r.getAddress()), "address Cannot Be Empty")
                .addValidator(r -> hasValue(r.getTelephoneNumber()), "telephone number  Cannot Be Empty")
                .addValidator(r -> hasValue(r.getEmail()), "email  Cannot Be Empty")
                .validate(createDto);
        joinViolations(violations);
    }

    private static StudentEntity mapToStudentEntity(CreateStudentRequest request) {
        return StudentEntity.builder()
                .arabicFullName(request.getArabicFullName())
                .englishFullName(request.getEnglishFullName())
                .email(request.getEmail())
                .telephoneNumber(request.getTelephoneNumber())
                .address(request.getAddress())
                .build();
    }

    private StudentResponse mapToStudentResponse(StudentEntity request) {
        return StudentResponse.builder()
                .arabicFullName(request.getArabicFullName())
                .englishFullName(request.getEnglishFullName())
                .email(request.getEmail())
                .telephoneNumber(request.getTelephoneNumber())
                .address(request.getAddress())
                .build();
    }
}
