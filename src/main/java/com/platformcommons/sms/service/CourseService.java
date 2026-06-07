package com.platformcommons.sms.service;

import com.platformcommons.sms.dto.CourseRequestDto;
import com.platformcommons.sms.entity.Course;

public interface CourseService {

    Course addCourse(CourseRequestDto dto);
}