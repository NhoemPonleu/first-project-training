package com.acledabankplc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnrollmentRequest {
    Long courseId;
    Long studentId;
}
