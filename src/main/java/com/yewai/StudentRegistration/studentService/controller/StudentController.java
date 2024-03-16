package com.yewai.StudentRegistration.studentService.controller;

import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import com.yewai.StudentRegistration.studentService.domain.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> register(
            @Valid @RequestBody StudentRequest studentRequest
            ) {
        var student = this.studentService.register(studentRequest);
        return ResponseEntity.ok(student);
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
