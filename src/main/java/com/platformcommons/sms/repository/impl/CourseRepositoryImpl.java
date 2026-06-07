package com.platformcommons.sms.repository.impl;

import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
            return course;
        }

        return entityManager.merge(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }
}
