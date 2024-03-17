package com.yewai.StudentRegistration.studentService.domain.service;

import com.yewai.StudentRegistration.exceptions.handler.EmailDuplicateException;
import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import com.yewai.StudentRegistration.studentService.domain.repository.CourseRepository;
import com.yewai.StudentRegistration.studentService.domain.repository.StudentRepository;
import com.yewai.StudentRegistration.studentService.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Transactional
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentDTO register(StudentRequest studentRequest) {
        var checkedStudent = this.studentRepository.existsByEmail(studentRequest.getEmail());
        if (checkedStudent.isPresent()) {
            throw new EmailDuplicateException("Email duplicated.", Map.of());
        }
        var student = this.studentRepository.save(studentMapper.toEntity(studentRequest));
        return studentMapper.toDTO(student);
    }

    public void enroll(Long studentId, Long courseId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + courseId));

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            studentRepository.save(student);
        }
    }
    public StudentDTO get(Long id) {
        var student = this.studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student Not Found"));
        return studentMapper.toDTO(student);
    }

    public Page<StudentDTO> getAll(int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        var students = this.studentRepository.findAll(pageRequest);
        var studentDTOList = this.studentMapper.toDTOList(students.getContent());
        return new PageImpl<>(studentDTOList, students.getPageable(), students.getTotalElements());
    }

    public StudentDTO update(Long id, StudentRequest studentRequest) {
        var student = this.studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student Not Found"));
        var checkedStudent = this.studentRepository.existsByEmail(studentRequest.getEmail());
        if (checkedStudent.isPresent() && !student.getEmail().equals(checkedStudent.get().getEmail()) ) {
            throw new EmailDuplicateException("Email duplicated.", Map.of());
        }
        student.setName(studentRequest.getName());
        student.setDob(studentRequest.getDob());
        student.setPhone(studentRequest.getPhone());
        student.setEmail(studentRequest.getEmail());
        student.setAddress(studentRequest.getAddress());

        var updatedStudent = this.studentRepository.save(student);
        return studentMapper.toDTO(updatedStudent);
    }

    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }

    public List<StudentDTO> getDiscountAllowedStudents() {
        var students = this.studentRepository.findStudentWithMoreThanThreeCourse();
        return this.studentMapper.toDTOList(students);
    }

    public List<StudentDTO> getStudentsCanGetOneCourseFree() {
        var students = this.studentRepository.findStudentsCanGetOneCourseFree();
        return this.studentMapper.toDTOList(students);
    }
}
