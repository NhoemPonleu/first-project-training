package com.acledabankplc.mapper;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    Course courseRequestToCourse(CourseRequest courseRequest);

    CourseRequest courseToCourseRequest(Course course);

    Course updateCourseFromDto(CourseRequest courseUpdateRequest, @MappingTarget Course course);
}
