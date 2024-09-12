package com.acledabankplc.controller;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.repository.EnrollmentDetailsDTO;
import com.acledabankplc.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    @PostMapping
    public ResponseEntity<?>registerEnrollment(@RequestBody EnrollmentRequest enrollmentRequest){
      EnrollmentResponse enrollmentResponse= enrollmentService.registerNewEnrollment(enrollmentRequest);
      return ResponseEntity.ok(enrollmentResponse);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<?>getStudentCourse(@PathVariable Long studentId){
        List<EnrollmentDetailsDTO> enrollmentResponse= enrollmentService.getEnrollmentDetailsForStudent(studentId);
        return ResponseEntity.ok(enrollmentResponse);
    }
}
