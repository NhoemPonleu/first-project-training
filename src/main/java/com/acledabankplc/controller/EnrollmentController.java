package com.acledabankplc.controller;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.dto.EnrollmentDetailsDTO;
import com.acledabankplc.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/enrollment")
@RequiredArgsConstructor
@Tag(name = "Enrollment  Controller"
        , description = "Controller to handle Enrollment Student operations.")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Enrollment Student"
            , description = "This endpoint to handle When Student Enrollment Study on Course And Just Role Admin can Register")
    public ResponseEntity<?> registerEnrollment(@RequestBody @Valid EnrollmentRequest enrollmentRequest) {
        EnrollmentResponse enrollmentResponse = enrollmentService.registerNewEnrollment(enrollmentRequest);
        return ResponseEntity.ok(enrollmentResponse);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Get Student Enrollment Course Detail"
            , description = "This endpoint To Track Information student Study on Course Detail.")
    public ResponseEntity<?> getStudentCourse(
            @RequestParam(value = "studentId", required = false) Long studentId,
            @RequestParam(value = "courseName", required = false) String courseName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        EnrollmentDetailsDTO enrollmentResponse= enrollmentService.getEnrollmentDetails(studentId, courseName, lastName);
        return ResponseEntity.ok(enrollmentResponse);

}
}