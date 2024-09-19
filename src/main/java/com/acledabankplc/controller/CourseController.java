package com.acledabankplc.controller;

import com.acledabankplc.baseResponse.BaseApi;
import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Course;
import com.acledabankplc.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
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
@Tag(name = "Course Controller",
        description = "Controller to handle Course Operations.")
public class CourseController {
    private final CourseService courseService;

    @Operation(summary = "Post Data",
            description = "This endpoint provides user submit data.")

    @PostMapping("/register/course")
//    public BaseApi<?> registerCourse(@RequestBody CourseRequest courseRequest) {
//        try {
//            Course savedCourse = courseService.registerNewCourse(courseRequest);
//            BaseApi<Course> response = BaseApi.<Course>builder()
//                    .message("Course registered successfully")
//                    .code(HttpStatus.OK.value())
//                    .status(true)
//                    .timeStamp(LocalDateTime.now())
//                    .data(savedCourse)
//                    .build();
//
//            return response;
//        } catch (Exception e) {
//            BaseApi<?> response = BaseApi.<Object>builder()
//                    .message("Failed to register course: " + e.getMessage())
//                    .code(HttpStatus.BAD_REQUEST.value())
//                    .status(false)
//                    .timeStamp(LocalDateTime.now())
//                    .data(null)
//                    .build();
//
//            return response;
//        }
//    }
    public ResponseEntity<BaseApi<?>> registerCourse(@RequestBody CourseRequest courseRequest) {
        Course savedCourse = courseService.registerNewCourse(courseRequest);

        BaseApi<Course> response = BaseApi.<Course>builder()
                .message("Course registered successfully")
                .code(HttpStatus.OK.value())
                .status(true)
                .timeStamp(LocalDateTime.now())
                .data(savedCourse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
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
