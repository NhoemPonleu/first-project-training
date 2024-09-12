package com.acledabankplc.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDetailsDTO {

    private Long enrollmentId;
    private LocalDate enrollmentDate;
    private String lastName;
    private String courseName;
}
