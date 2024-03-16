package com.yewai.StudentRegistration.studentService.domain.repository;

import com.yewai.StudentRegistration.studentService.domain.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
