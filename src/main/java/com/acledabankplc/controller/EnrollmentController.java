package com.acledabankplc.controller;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.dto.EnrollmentDetailsDTO;
import com.acledabankplc.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> registerEnrollment(@RequestBody @Valid EnrollmentRequest enrollmentRequest) {
        EnrollmentResponse enrollmentResponse = enrollmentService.registerNewEnrollment(enrollmentRequest);
        return ResponseEntity.ok(enrollmentResponse);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentCourse(@PathVariable("studentId") Long studentId) {
        EnrollmentDetailsDTO enrollmentResponse = enrollmentService.getEnrollmentDetails(studentId);
        return ResponseEntity.ok(enrollmentResponse);
    }
}
