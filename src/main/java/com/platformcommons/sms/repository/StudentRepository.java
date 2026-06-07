package com.platformcommons.sms.repository;

import com.platformcommons.sms.entity.Student;
import com.platformcommons.sms.entity.Course;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(Long id);

    Optional<Student> findByStudentCode(String studentCode);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findStudentsByCourseId(Long courseId);

    List<Course> findAssignedCoursesByKeyword(Long studentId, String keyword);

    Optional<Student> findByStudentCodeAndDateOfBirth(
            String studentCode,
            LocalDate dateOfBirth
    );
}
