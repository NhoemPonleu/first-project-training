package com.acledabankplc.dto;

import lombok.Data;

@Data
public class CourseRequest {
    private Long id;
    private String courseName;
    private String courseCode;
    private String description;
}
