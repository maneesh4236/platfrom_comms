package com.platformcommons.sms.service;

import com.platformcommons.sms.entity.Student;
import com.platformcommons.sms.repository.StudentRepository;
import com.platformcommons.sms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void shouldFindStudentsByName() {

        Student student = Student.builder()
                .id(1L)
                .name("Maneesh")
                .build();

        when(studentRepository
                .findByNameContainingIgnoreCase("Man"))
                .thenReturn(List.of(student));

        List<Student> result =
                studentService.getStudentsByName("Man");

        assertEquals(1, result.size());

        assertEquals(
                "Maneesh",
                result.get(0).getName()
        );
    }
}
