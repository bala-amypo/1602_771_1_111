package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profile API")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile createStudent(@RequestBody StudentProfile profile) {
        return service.createStudent(profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @GetMapping
    public List<StudentProfile> getAllStudents() {
        return service.getAllStudents();
    }

    @PutMapping("/{id}/status")
    public StudentProfile updateStudentStatus(@PathVariable Long id,
                                              @RequestParam boolean active) {
        return service.updateStudentStatus(id, active);
    }

    @GetMapping("/lookup/{studentId}")
    public StudentProfile findByStudentId(@PathVariable String studentId) {
        return service.findByStudentId(studentId);
    }
}
