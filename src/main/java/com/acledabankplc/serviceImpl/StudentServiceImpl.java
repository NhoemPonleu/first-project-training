package com.acledabankplc.serviceImpl;

import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.exception.ResourceNotFoundException;

import com.acledabankplc.mapper.StudentMapper;
import com.acledabankplc.model.Course;
import com.acledabankplc.model.Student;
import com.acledabankplc.repository.StudentRepository;
import com.acledabankplc.service.CourseService;
import com.acledabankplc.service.StudentService;
import com.acledabankplc.utils.UserAuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseService courseService;
    private final UserAuthenticationUtils userAuthenticationUtils;
    @Override
    public StudentDTO registerNewStudent(StudentDTO studentDTO) {
        // check if course not found
        Course course= courseService.retrieveCourseById(studentDTO.getCourseId());
        Student student= studentMapper.studentDTOToStudent(studentDTO);
        student.setRegisterDate(LocalDate.now());
//        student.setCreatedBy(userAuthenticationUtils.getUserRequestDTO().getUserId());
//        student.setCreatedByUserRole(userAuthenticationUtils.getUserRequestDTO().getUserRole());
        Student savedStudent = studentRepository.save(student);
        StudentDTO studentDTO1= studentMapper.studentToStudentDTO(savedStudent);
        return studentDTO1;
    }

    @Override
    public Student inquiryStudentById(Long studentId) {
       Student student= studentRepository.findById(studentId).orElseThrow(()
               ->new ResourceNotFoundException("student",studentId));
        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(StudentDTO studentDTO,Long studentId) {
       Student student= inquiryStudentById(studentId);
       Student updateStudent= studentMapper.updateStudentFromDTO(studentDTO,student);
       Course course=courseService.retrieveCourseById(studentDTO.getCourseId());
       updateStudent.setCourse(course);
      // updateStudent.setLastModifiedBy(userAuthenticationUtils.getUserRequestDTO().getUserId());
      // updateStudent.setUpdatedByUserRole(userAuthenticationUtils.getUserRequestDTO().getUserRole());
        return studentRepository.save(updateStudent);
    }

    @Override
    public Page<Student> findAllStudents(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return studentRepository.findAll(pageable);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student=inquiryStudentById(studentId);
        studentRepository.deleteById(student.getId());
    }
}
