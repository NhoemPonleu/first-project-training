package com.acledabankplc.service;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.dto.EnrollmentDetailsDTO;

public interface EnrollmentService {
    EnrollmentResponse registerNewEnrollment(EnrollmentRequest enrollmentRequest);

    EnrollmentDetailsDTO getEnrollmentDetails(Long studentId, String courseName, String lastName);

}
