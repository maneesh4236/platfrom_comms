package com.platformcommons.sms.service;

import com.platformcommons.sms.dto.StudentProfileUpdateDto;
import com.platformcommons.sms.dto.StudentRequestDto;
import com.platformcommons.sms.dto.VerifyStudentDto;
import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Student addStudent(StudentRequestDto dto);
    boolean verifyStudent(VerifyStudentDto dto);
    void assignCourse(Long studentId, Long courseId);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByCourse(Long courseId);
    List<Course> searchAssignedCourses(Long studentId, String keyword);
    Student updateProfile(
            Long studentId,
            StudentProfileUpdateDto dto
    );

    Set<Course> getAssignedCourses(Long studentId);
    void leaveCourse(
            Long studentId,
            Long courseId
    );
}
