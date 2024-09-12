package com.acledabankplc.service;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.repository.EnrollmentDetailsDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse registerNewEnrollment(EnrollmentRequest enrollmentRequest);
    List<EnrollmentDetailsDTO> getEnrollmentDetailsForStudent(Long studentId);

}
