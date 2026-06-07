package com.platformcommons.sms.controller;


import com.platformcommons.sms.dto.StudentProfileUpdateDto;
import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.entity.Student;
import com.platformcommons.sms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PutMapping("/profile/{studentId}")
    public Student updateProfile(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentProfileUpdateDto dto
    ) {
        return studentService.updateProfile(studentId, dto);
    }
    @GetMapping("/{studentId}/courses")
    public Set<Course> getAssignedCourses(
            @PathVariable Long studentId
    ) {
        return studentService.getAssignedCourses(studentId);
    }

    @GetMapping("/{studentId}/courses/search")
    public List<Course> searchAssignedCourses(
            @PathVariable Long studentId,
            @RequestParam String keyword
    ) {
        return studentService.searchAssignedCourses(studentId, keyword);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public String leaveCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {

        studentService.leaveCourse(
                studentId,
                courseId
        );

        return "Course Removed Successfully";
    }
}
