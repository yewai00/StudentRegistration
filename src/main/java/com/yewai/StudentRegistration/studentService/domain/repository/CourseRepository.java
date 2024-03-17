package com.yewai.StudentRegistration.studentService.domain.repository;

import com.yewai.StudentRegistration.studentService.domain.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
