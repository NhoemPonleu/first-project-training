package com.acledabankplc.service;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;

public interface EnrollmentService {
    EnrollmentResponse registerNewEnrollment(EnrollmentRequest enrollmentRequest);

}
