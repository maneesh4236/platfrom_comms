package com.platformcommons.sms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    private String description;

    private String courseType;

    private Integer duration;

    private String topics;

    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}