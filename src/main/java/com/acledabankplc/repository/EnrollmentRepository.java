package com.acledabankplc.repository;

import com.acledabankplc.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);

    @Query(value = """
       SELECT e.enrollment_id AS enrollmentId, e.enrollment_date AS enrollmentDate,
              ts.last_name AS lastName, c.course_name AS courseName
       FROM enrollment e
       LEFT JOIN course c ON e.course_id = c.id
       LEFT JOIN tbl_students ts ON e.student_id = ts.id
       WHERE e.student_id = :studentId
       """, nativeQuery = true)
    List<Object[]> findEnrollmentDetailsByStudentId(@Param("studentId") Long studentId);

    @Query(value = """
       SELECT COUNT(DISTINCT e.course_id) 
       FROM enrollment e 
       WHERE e.student_id = :studentId
       """, nativeQuery = true)
    Long findTotalCourseCountByStudentId(@Param("studentId") Long studentId);


}
