package com.acledabankplc.service.serviceImpl;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.exception.AlreadyExistsException;
import com.acledabankplc.mapper.EnrollmentMapper;
import com.acledabankplc.model.Course;
import com.acledabankplc.model.Enrollment;
import com.acledabankplc.model.Student;
import com.acledabankplc.repository.CourseDetailDTO;
import com.acledabankplc.repository.EnrollmentDetailsDTO;
import com.acledabankplc.repository.EnrollmentRepository;
import com.acledabankplc.service.CourseService;
import com.acledabankplc.service.EnrollmentService;
import com.acledabankplc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.SQLQueryParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public EnrollmentDetailsDTO getEnrollmentDetails(Long studentId) {
        // Get the total count of courses
        Long totalCountCourse = enrollmentRepository.findTotalCourseCountByStudentId(studentId);

        // Get the detailed list of course enrollments
        List<Object[]> results = enrollmentRepository.findEnrollmentDetailsByStudentId(studentId);

        String lastName = null;
        List<CourseDetailDTO> courseDetailList = new ArrayList<>();

        // Loop through the query results and map them to DTOs
        for (Object[] result : results) {
            if (lastName == null) {
                lastName = (String) result[2];  // Get student's last name (once)
            }
            CourseDetailDTO courseDetailDTO = new CourseDetailDTO(
                    (String) result[3],  // courseName
                    (Long) result[0],    // enrollmentId
                    (Date) result[1]     // enrollmentDate
            );

            // Add each course to the list
            courseDetailList.add(courseDetailDTO);
        }

        // Return the final DTO with the student's last name, total course count, and list of courses
        return new EnrollmentDetailsDTO(lastName, totalCountCourse, courseDetailList);
    }

}
