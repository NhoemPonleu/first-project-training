package com.acledabankplc.controller;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.model.Course;
import com.acledabankplc.service.CourseService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/course")
@PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/register/course")
    public ResponseEntity<Course> registerNewCourse(@RequestBody CourseRequest courseRequest) {
        Course course = courseService.registerNewCourse(courseRequest);
        return new ResponseEntity<>(course,HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> updateExistingCourse(@PathVariable("id") Long courseId, @RequestBody CourseRequest courseRequest) {
        Course course = courseService.updateCourse(courseRequest, courseId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("id") Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok("Course Deleted");
    }

    @GetMapping("/all")
    @PermitAll
    public ResponseEntity<?> findAllCourse(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Course> allCourse = courseService.findAllCourse(page, size);
        return ResponseEntity.ok(allCourse);
    }
}
