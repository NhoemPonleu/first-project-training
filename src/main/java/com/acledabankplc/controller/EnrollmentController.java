package com.acledabankplc.controller;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
