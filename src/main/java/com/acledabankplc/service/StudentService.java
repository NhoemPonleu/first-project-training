package com.acledabankplc.service;

import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    StudentDTO registerNewStudent(StudentDTO studentDTO);

    Student inquiryStudentById(Long studentId);

    Student updateStudent(StudentDTO studentDTO, Long studentId);

    Page<Student> findAllStudents(int pageNo, int pageSize);

    void deleteStudentById(Long studentId);
}
