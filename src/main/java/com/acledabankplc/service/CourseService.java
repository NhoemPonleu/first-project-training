package com.acledabankplc.service;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.model.Course;
import org.springframework.data.domain.Page;

public interface CourseService {
    Course retrieveCourseById(Long courseId);
    Course registerNewCourse(CourseRequest courseRequest);
    Course updateCourse(CourseRequest courseRequest,Long courseId);
    Course findCourseById(Long courseId);
    void deleteCourseById(Long courseId);
    Page<Course>findAllCourse(int pageNumber,int pageSize);
}
