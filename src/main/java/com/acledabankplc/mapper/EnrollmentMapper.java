package com.acledabankplc.mapper;

import com.acledabankplc.dto.EnrollmentRequest;
import com.acledabankplc.dto.EnrollmentResponse;
import com.acledabankplc.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    // Map EnrollmentRequest to Enrollment
    @Mapping(target = "student.id", source = "studentId")
    @Mapping(target = "course.id", source = "courseId")
    Enrollment toEnrollment(EnrollmentRequest enrollmentRequest);

    // Map Enrollment to EnrollmentResponse
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "student.firstName", target = "studentName")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "enrollmentDate", target = "enrollmentDate")
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
}
