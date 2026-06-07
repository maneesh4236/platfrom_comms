package com.platformcommons.sms.repository.impl;

import com.platformcommons.sms.entity.Course;
import com.platformcommons.sms.entity.Student;
import com.platformcommons.sms.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
            return student;
        }

        return entityManager.merge(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public Optional<Student> findByStudentCode(String studentCode) {
        return entityManager
                .createQuery(
                        "SELECT s FROM Student s WHERE s.studentCode = :studentCode",
                        Student.class
                )
                .setParameter("studentCode", studentCode)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Student> findByNameContainingIgnoreCase(String name) {
        return entityManager
                .createQuery(
                        """
                        SELECT s
                        FROM Student s
                        WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))
                        """,
                        Student.class
                )
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) {
        return entityManager
                .createQuery(
                        """
                        SELECT s
                        FROM Student s
                        JOIN s.courses c
                        WHERE c.id = :courseId
                        """,
                        Student.class
                )
                .setParameter("courseId", courseId)
                .getResultList();
    }

    @Override
    public List<Course> findAssignedCoursesByKeyword(
            Long studentId,
            String keyword
    ) {
        return entityManager
                .createQuery(
                        """
                        SELECT c
                        FROM Student s
                        JOIN s.courses c
                        WHERE s.id = :studentId
                        AND (
                            LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                            OR LOWER(c.topics) LIKE LOWER(CONCAT('%', :keyword, '%'))
                            OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
                        )
                        """,
                        Course.class
                )
                .setParameter("studentId", studentId)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    @Override
    public Optional<Student> findByStudentCodeAndDateOfBirth(
            String studentCode,
            LocalDate dateOfBirth
    ) {
        return entityManager
                .createQuery(
                        """
                        SELECT s
                        FROM Student s
                        WHERE s.studentCode = :studentCode
                        AND s.dateOfBirth = :dateOfBirth
                        """,
                        Student.class
                )
                .setParameter("studentCode", studentCode)
                .setParameter("dateOfBirth", dateOfBirth)
                .getResultStream()
                .findFirst();
    }
}
