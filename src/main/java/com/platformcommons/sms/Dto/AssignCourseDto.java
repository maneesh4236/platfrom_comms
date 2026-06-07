package com.platformcommons.sms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignCourseDto {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Course id is required")
    private Long courseId;
}
