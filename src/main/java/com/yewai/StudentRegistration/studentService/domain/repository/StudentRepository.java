package com.yewai.StudentRegistration.studentService.domain.repository;

import com.yewai.StudentRegistration.studentService.domain.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("FROM Student S WHERE S.email = :email")
    public Optional<Student> existsByEmail(String email);

    @Query("SELECT s FROM Student s JOIN s.courses c GROUP BY s HAVING COUNT(c) > 3")
    List<Student> findStudentWithMoreThanThreeCourse();

    @Procedure("get_free_one_course_students")
    List<Student> findStudentsCanGetOneCourseFree();

}
