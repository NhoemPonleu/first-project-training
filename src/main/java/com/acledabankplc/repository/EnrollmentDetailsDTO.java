package com.acledabankplc.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDetailsDTO {
    private String lastName;
    private Long totalCountCourse;

    private List<CourseDetailDTO> courseDetailDTOList;
}
