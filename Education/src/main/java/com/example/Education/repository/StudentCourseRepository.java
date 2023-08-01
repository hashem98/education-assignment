package com.example.Education.repository;

import com.example.Education.model.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Long> {
    List<StudentCourseEntity> findAllByStudentId(Long studentId);
    List<StudentCourseEntity> findAllByCourseId(Long courseId);
}
