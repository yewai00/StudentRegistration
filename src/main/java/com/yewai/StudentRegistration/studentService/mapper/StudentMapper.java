package com.yewai.StudentRegistration.studentService.mapper;

import com.yewai.StudentRegistration.studentService.domain.model.dto.CourseDTO;
import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.entity.Course;
import com.yewai.StudentRegistration.studentService.domain.model.entity.Student;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    @Autowired
    private CourseMapper courseMapper;

    public Student toEntity(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.getName())
                .dob(studentRequest.getDob())
                .email(studentRequest.getEmail())
                .phone(studentRequest.getPhone())
                .address(studentRequest.getAddress())
                .build();
    }

    public StudentDTO toDTO(Student student)  {
        var courseDTOList = this.courseMapper.toDTOList(
                student.getCourses() != null ? student.getCourses() : List.of()
        );

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getDob(),
                student.getPhone(),
                student.getEmail(),
                student.getAddress(),
                courseDTOList
        );
    }

    public List<StudentDTO> toDTOList(List<Student> students) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (var student: students) {
            studentDTOList.add(this.toDTO(student));
        }
        return studentDTOList;
    }
}
