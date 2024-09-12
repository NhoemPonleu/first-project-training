package com.acledabankplc.repository;

import com.acledabankplc.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
}
