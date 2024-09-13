package com.acledabankplc.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
    private String courseName;
    private Long enrollmentId;
    private Date enrollmentDate;
}
