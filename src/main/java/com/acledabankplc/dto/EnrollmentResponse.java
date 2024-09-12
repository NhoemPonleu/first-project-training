package com.acledabankplc.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnrollmentResponse {
    String courseName;
    String studentName;
    Long studentId;
    LocalDate enrollmentDate;
}
