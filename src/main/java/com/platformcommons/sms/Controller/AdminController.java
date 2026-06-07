package com.platformcommons.sms.controller;


import com.platformcommons.sms.dto.AssignCourseDto;
import com.platformcommons.sms.dto.CourseRequestDto;
import com.platformcommons.sms.dto.StudentRequestDto;
import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.entity.Student;
import com.platformcommons.sms.service.CourseService;
import com.platformcommons.sms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;

    @PostMapping("/students")
    public Student addStudent(
            @Valid @RequestBody StudentRequestDto dto
    ) {
        return studentService.addStudent(dto);
    }

    @PostMapping("/courses")
    public Course addCourse(
            @Valid @RequestBody CourseRequestDto dto
    ) {
        return courseService.addCourse(dto);
    }

    @PostMapping("/assign-course")
    public String assignCourse(
            @Valid @RequestBody AssignCourseDto dto
    ) {

        studentService.assignCourse(
                dto.getStudentId(),
                dto.getCourseId()
        );

        return "Course Assigned Successfully";
    }

    @GetMapping("/students/search")
    public List<Student> searchStudents(
            @RequestParam String name
    ) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> getStudentsByCourse(
            @PathVariable Long courseId
    ) {
        return studentService.getStudentsByCourse(courseId);
    }
}
