package com.yewai.StudentRegistration.studentService.domain.service;

import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import com.yewai.StudentRegistration.studentService.domain.repository.StudentRepository;
import com.yewai.StudentRegistration.studentService.mapper.CourseMapper;
import com.yewai.StudentRegistration.studentService.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    public StudentDTO register(StudentRequest studentRequest) {
        var student = this.studentRepository.save(studentMapper.toEntity(studentRequest));
        return studentMapper.toDTO(
                student,
                courseMapper.toDTOList(
                        student.getCourses() != null ?
                                student.getCourses() : List.of())
        );
    }

    public ResponseEntity<?> enroll() {
        return null;
    }
    public ResponseEntity<?> get() {
        return null;
    }

    public ResponseEntity<?> getAll() {
        return null;
    }

    public ResponseEntity<?> update() {
        return null;
    }

    public ResponseEntity<?> delete() {
        return null;
    }
}
