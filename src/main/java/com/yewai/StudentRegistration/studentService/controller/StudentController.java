package com.yewai.StudentRegistration.studentService.controller;

import com.yewai.StudentRegistration.studentService.domain.model.dto.StudentDTO;
import com.yewai.StudentRegistration.studentService.domain.model.request.StudentRequest;
import com.yewai.StudentRegistration.studentService.domain.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.created(null).body(student);
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {
        this.studentService.enroll(studentId, courseId);
        return ResponseEntity.ok("Enrollment Successfully.") ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable Long id) {
        var student = this.studentService.get(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var students = this.studentService.getAll(page, size);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest studentRequest
            ) {
        return ResponseEntity.ok(this.studentService.update(id, studentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.studentService.delete(id);
        return ResponseEntity.ok("Deleted Successfully.");
    }

    @GetMapping("/discount-eligible")
    public ResponseEntity<List<StudentDTO>> getDiscountAllowedStudentsList() {
        var students = this.studentService.getDiscountAllowedStudents();
        return ResponseEntity.ok(students);
    }

}
