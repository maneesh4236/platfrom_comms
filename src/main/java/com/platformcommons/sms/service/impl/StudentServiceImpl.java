package com.platformcommons.sms.service.impl;

import com.platformcommons.sms.dto.StudentProfileUpdateDto;
import com.platformcommons.sms.dto.StudentRequestDto;
import com.platformcommons.sms.dto.VerifyStudentDto;
import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.entity.Student;
import com.platformcommons.sms.exception.ResourceNotFoundException;
import com.platformcommons.sms.mapper.StudentMapper;
import com.platformcommons.sms.repository.CourseRepository;
import com.platformcommons.sms.repository.StudentRepository;
import com.platformcommons.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Student addStudent(StudentRequestDto dto) {
        return studentRepository.save(StudentMapper.toEntity(dto));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean verifyStudent(VerifyStudentDto dto) {
        return studentRepository
                .findByStudentCodeAndDateOfBirth(
                        dto.getStudentCode(),
                        dto.getDateOfBirth()
                )
                .isPresent();
    }

    @Override
    @Transactional
    public void assignCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        student.getCourses().add(course);

        studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByCourse(Long courseId) {
        return studentRepository.findStudentsByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> searchAssignedCourses(Long studentId, String keyword) {
        return studentRepository.findAssignedCoursesByKeyword(studentId, keyword);
    }

    @Override
    @Transactional
    public Student updateProfile(
            Long studentId,
            StudentProfileUpdateDto dto
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        student.setEmail(dto.getEmail());
        student.setMobileNumber(dto.getMobileNumber());
        student.setParentsName(dto.getParentsName());

        if (dto.getAddresses() != null) {
            StudentMapper.replaceAddresses(student, dto.getAddresses());
        }

        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Course> getAssignedCourses(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        return student.getCourses();
    }

    @Override
    @Transactional
    public void leaveCourse(
            Long studentId,
            Long courseId
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        student.getCourses().remove(course);

        studentRepository.save(student);
    }
}
