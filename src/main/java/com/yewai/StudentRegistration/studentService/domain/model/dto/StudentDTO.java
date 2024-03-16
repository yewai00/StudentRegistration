package com.yewai.StudentRegistration.studentService.domain.model.dto;

import java.time.LocalDate;
import java.util.List;

public record StudentDTO(
        Long id,
        String name,
        LocalDate dob,
        String phone,
        String email,
        String address,
        List<CourseDTO> courses
) {
}
