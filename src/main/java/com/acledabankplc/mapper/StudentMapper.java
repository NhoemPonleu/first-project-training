package com.acledabankplc.mapper;

import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Course;
import com.acledabankplc.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Convert Student entity to StudentDTO
    @Mapping(source = "course.id", target = "courseId")
    StudentDTO studentToStudentDTO(Student student);

    @Mapping(source = "courseId", target = "course", qualifiedByName = "mapCourse") // Maps courseId to Course entity
    Student studentDTOToStudent(StudentDTO studentDTO);

    // Update existing Student entity from StudentDTO
   // @Mapping(target = "id", ignore = true)
    //@Mapping(source = "courseId", target = "course", qualifiedByName = "mapCourse")
    Student updateStudentFromDTO(StudentDTO studentDTO, @MappingTarget Student student);

    // Method to map course ID to Course entity
    @Named("mapCourse")
    default Course mapCourse(Long courseId) {
        if (courseId == null) {
            return null;
        }
        Course course = new Course();
        course.setId(courseId);
        return course;
    }

    // Method to map Course entity to course ID
    @Named("mapCourseId")
    default Long mapCourseId(Course course) {
        return course != null ? course.getId() : null;
    }
}
