package com.platformcommons.sms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequestDto {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Course type is required")
    private String courseType;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 day")
    private Integer duration;

    @NotBlank(message = "Topics are required")
    private String topics;
}
