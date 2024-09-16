package com.acledabankplc.controller;

import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Student;
import com.acledabankplc.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/acstudent/v1")
@PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerNewStudent(@RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO student = studentService.registerNewStudent(studentDTO);
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> inquiryStudentById(@PathVariable("id") Long studentId) {
        Student student = studentService.inquiryStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('management:update')")
    public ResponseEntity<?> updateStudent(
            @PathVariable("id") Long studentId,
            @RequestBody StudentDTO studentDTO) {

        Student updatedStudent = studentService.updateStudent(studentDTO, studentId);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Page<?>> findAllStudent(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Student> products = studentService.findAllStudents(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Data has been Deleted");
    }
}
