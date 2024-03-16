package com.yewai.StudentRegistration.studentService.mapper;

import com.yewai.StudentRegistration.studentService.domain.model.dto.CourseDTO;
import com.yewai.StudentRegistration.studentService.domain.model.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getYear()
        );
    }

    public List<CourseDTO> toDTOList(List<Course> courses) {
        List<CourseDTO> courseDTOs = new ArrayList<>();
         for (var course: courses) {
            courseDTOs.add(this.toDTO(course));
         }
         return courseDTOs;
    }
}
