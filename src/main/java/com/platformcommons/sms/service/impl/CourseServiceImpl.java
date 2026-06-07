package com.platformcommons.sms.service.impl;

import com.platformcommons.sms.dto.CourseRequestDto;
import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.mapper.CourseMapper;
import com.platformcommons.sms.repository.CourseRepository;
import com.platformcommons.sms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Course addCourse(CourseRequestDto dto) {
        return courseRepository.save(CourseMapper.toEntity(dto));
    }
}
