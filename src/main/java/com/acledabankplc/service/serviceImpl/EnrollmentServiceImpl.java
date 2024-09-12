package com.acledabankplc.service.serviceImpl;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.exception.AlreadyExistsException;
import com.acledabankplc.mapper.EnrollmentMapper;
import com.acledabankplc.model.Course;
import com.acledabankplc.model.Enrollment;
import com.acledabankplc.model.Student;
import com.acledabankplc.repository.EnrollmentRepository;
import com.acledabankplc.service.CourseService;
import com.acledabankplc.service.EnrollmentService;
import com.acledabankplc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentService studentService;
    private final CourseService courseService;
    @Override
    public EnrollmentResponse registerNewEnrollment(EnrollmentRequest enrollmentRequest) {
        boolean isEnrolled = enrollmentRepository.existsByStudent_IdAndCourse_Id(
                enrollmentRequest.getStudentId(),
                enrollmentRequest.getCourseId()
        );

        if (isEnrolled) {
            throw new AlreadyExistsException("Student is already enrolled in this course.");
        }
       Student student= studentService.inquiryStudentById(enrollmentRequest.getStudentId());
       Course course=courseService.findCourseById(enrollmentRequest.getCourseId());
       Enrollment enrollment= enrollmentMapper.toEnrollment(enrollmentRequest);
       enrollment.setStudent(student);
       enrollment.setCourse(course);
       enrollment.setEnrollmentDate(LocalDate.now());
       EnrollmentResponse enrollmentResponse= enrollmentMapper.toEnrollmentResponse(enrollment);
       enrollmentRepository.save(enrollment);
        return enrollmentResponse;
    }
}
