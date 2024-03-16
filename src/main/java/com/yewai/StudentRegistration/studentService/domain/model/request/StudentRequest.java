package com.yewai.StudentRegistration.studentService.domain.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {

    @NotEmpty(message = "Name is required field.")
    @Size(max = 255)
    private String name;

    private LocalDate dob;

    @NotEmpty(message = "Phone is required field.")
    @Size(max = 255)
    private String phone;

    @NotEmpty(message = "email is required field.")
    @Size(max = 255)
    @Email
    private String email;

    @NotEmpty(message = "address is required field.")
    @Size(max = 255)
    private String address;
}
