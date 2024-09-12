package com.acledabankplc.mapper;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    Course courseRequestToCourse(CourseRequest courseRequest);

    CourseRequest courseToCourseRequest(Course course);

//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "lastModifiedDate", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "lastModifiedBy", ignore = true)
    Course updateCourseFromDto(CourseRequest courseUpdateRequest, @MappingTarget Course course);
}
