package com.acledabankplc.service.serviceImpl;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.exception.BusinessException;
import com.acledabankplc.exception.ResourceNotFoundException;
import com.acledabankplc.mapper.CourseMapper;
import com.acledabankplc.model.Course;
import com.acledabankplc.repository.CourseRepository;
import com.acledabankplc.service.CourseService;
import com.acledabankplc.utils.UserAuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;
    private final UserAuthenticationUtils userAuthenticationUtils;

    @Override
    public Course retrieveCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()
                -> new ResourceNotFoundException("Course", courseId));
        return course;
    }

    @Override
    public Course registerNewCourse(CourseRequest courseRequest) {
        if ("009".equals(courseRequest.getCourseCode())) {
            throw new BusinessException("Simulated error during course registration.");
        }
        logger.info("Registering new course with request: {}", courseRequest);
        Course course = courseMapper.courseRequestToCourse(courseRequest);
        Course course1 = courseRepository.save(course);
        logger.info("Successfully registered new course: {}", course1);
        return course1;
    }

    @Override
    public Course updateCourse(CourseRequest courseRequest, Long courseId) {
        Course existingCourse = findCourseById(courseId);
        Course course = courseMapper.updateCourseFromDto(courseRequest, existingCourse);
        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Course", courseId));
    }

    @Override
    public void deleteCourseById(Long courseId) {
        Course course = findCourseById(courseId);
        courseRepository.deleteById(courseId);
    }

    @Override
    public Page<Course> findAllCourse(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return courseRepository.findAll(pageable);
    }
}
