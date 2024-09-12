package com.acledabankplc.controller;

import com.acledabankplc.baseResponse.BaseApi;
import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Student;
import com.acledabankplc.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public BaseApi<?> inquiryStudentById(@PathVariable("id") Long studentId) {
        Student student = studentService.inquiryStudentById(studentId);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Student Inquiry Successfully")
                .timeStamp(LocalDateTime.now())
                .data(student)
                .status(true)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('management:update')")
    public BaseApi<?> updateStudent(
            @PathVariable("id") Long studentId,
            @RequestBody StudentDTO studentDTO) {

        Student updatedStudent = studentService.updateStudent(studentDTO, studentId);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Student Updated Successfully")
                .timeStamp(LocalDateTime.now())
                .data(updatedStudent)
                .status(true)
                .build();
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
    public BaseApi<?> deleteStudentById(@PathVariable("id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Student deleted Successfully")
                .timeStamp(LocalDateTime.now())
                .status(true)
                .build();
    }
}
