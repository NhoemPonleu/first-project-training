package com.acledabankplc.controller;

import com.acledabankplc.baseResponse.BaseApi;
import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.model.Course;
import com.acledabankplc.service.CourseService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public BaseApi<?>registerNewCourse(@RequestBody CourseRequest courseRequest){
       Course course =courseService.registerNewCourse(courseRequest);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Course register Successfully")
                .timeStamp(LocalDateTime.now())
                .data(course)
                .status(true)
                .build();
    }
    @PutMapping("{id}")
    public BaseApi<?>updateExistingCourse(@PathVariable("id") Long courseId, @RequestBody CourseRequest courseRequest){
        Course course =courseService.updateCourse(courseRequest,courseId);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Course updated Successfully")
                .timeStamp(LocalDateTime.now())
                .data(course)
                .status(true)
                .build();
    }
    @DeleteMapping("{id}")
    public BaseApi<?>deleteCourseById(@PathVariable("id") Long courseId){
        courseService.deleteCourseById(courseId);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Course Delete Successfully")
                .timeStamp(LocalDateTime.now())
                .data("non")
                .status(true)
                .build();
    }
    @GetMapping("/all")
    @PermitAll
    public BaseApi<?>findAllCourse(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
       Page<Course> allCourse= courseService.findAllCourse(page, size);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Course Find Successfully")
                .timeStamp(LocalDateTime.now())
                .data(allCourse)
                .status(true)
                .build();
    }
}
