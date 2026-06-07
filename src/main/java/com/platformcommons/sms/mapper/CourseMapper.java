package com.platformcommons.sms.mapper;

import com.platformcommons.sms.dto.CourseRequestDto;
import com.platformcommons.sms.entity.Course;

public final class CourseMapper {

    private CourseMapper() {
    }

    public static Course toEntity(CourseRequestDto dto) {
        return Course.builder()
                .courseName(dto.getCourseName())
                .description(dto.getDescription())
                .courseType(dto.getCourseType())
                .duration(dto.getDuration())
                .topics(dto.getTopics())
                .build();
    }
}
