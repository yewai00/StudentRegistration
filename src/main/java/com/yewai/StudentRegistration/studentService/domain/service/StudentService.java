package com.yewai.StudentRegistration.studentService.domain.service;

import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.entity.Student;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import com.yewai.StudentRegistration.studentService.domain.repository.StudentRepository;
import com.yewai.StudentRegistration.studentService.mapper.CourseMapper;
import com.yewai.StudentRegistration.studentService.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public StudentDTO get(Long id) {
        var student = this.studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student Not Found"));

        return studentMapper.toDTO(
                student,
                courseMapper.toDTOList(
                        student.getCourses() != null ?
                                student.getCourses() : List.of())
        );
    }

    public Page<StudentDTO> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        var students = this.studentRepository.findAll(pageRequest);
        for (var student: students) {
            studentDTOList.add(
                    studentMapper.toDTO(
                        student,
                        courseMapper.toDTOList(
                            student.getCourses() != null ? student.getCourses() : List.of()
                        )
                    )
            );
        }
        return new PageImpl<>(studentDTOList, students.getPageable(), students.getTotalElements());
    }

    public StudentDTO update(Long id, StudentRequest studentRequest) {
        var student = this.studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student Not Found"));
        var studentEntity = this.studentMapper.toEntity(studentRequest);
        studentEntity.setId(id);
        var updatedStudent = this.studentRepository.save(studentEntity);
        return studentMapper.toDTO(
                updatedStudent,
                courseMapper.toDTOList(
                        updatedStudent.getCourses() != null ?
                                student.getCourses() : List.of()
                )
        );
    }

    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }
}
