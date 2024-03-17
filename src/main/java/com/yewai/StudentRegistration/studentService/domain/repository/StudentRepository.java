package com.yewai.StudentRegistration.studentService.domain.repository;

import com.yewai.StudentRegistration.studentService.domain.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s JOIN s.courses c GROUP BY s HAVING COUNT(c) > 3")
    List<Student> findStudentWithMoreThanThreeCourse();

}
