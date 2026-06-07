package com.platformcommons.sms.repository;

import com.platformcommons.sms.entity.Course;

import java.util.Optional;

public interface CourseRepository {

    Course save(Course course);

    Optional<Course> findById(Long id);
}
