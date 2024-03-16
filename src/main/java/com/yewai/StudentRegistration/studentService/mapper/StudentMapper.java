package com.yewai.StudentRegistration.studentService.mapper;

import com.yewai.StudentRegistration.studentService.domain.model.dto.CourseDTO;
import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.entity.Student;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.getName())
                .dob(studentRequest.getDob())
                .email(studentRequest.getEmail())
                .phone(studentRequest.getPhone())
                .address(studentRequest.getAddress())
                .build();
    }

    public StudentDTO toDTO(Student student, List<CourseDTO> courseDTOS)  {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getDob(),
                student.getPhone(),
                student.getEmail(),
                student.getAddress(),
                courseDTOS
        );
    }
}
