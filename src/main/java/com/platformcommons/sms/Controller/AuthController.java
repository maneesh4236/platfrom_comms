package com.platformcommons.sms.controller;

import com.platformcommons.sms.dto.VerifyStudentDto;
import com.platformcommons.sms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "Admin Logged In";
    }

    @PostMapping("/student/verify")
    public String verifyStudent(
            @Valid @RequestBody VerifyStudentDto dto
    ) {

        boolean verified = studentService.verifyStudent(dto);

        return verified
                ? "Student Verified"
                : "Invalid Student";
    }
}
