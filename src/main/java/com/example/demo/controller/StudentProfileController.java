package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles")
public class StudentProfileController {
    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createProfile(@RequestBody StudentProfileDto dto) {
        StudentProfile profile = service.createProfile(dto, dto.getUserId());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateProfile(@PathVariable Long id,
                                                        @RequestBody StudentProfileDto dto) {
        StudentProfile profile = service.updateProfile(id, dto);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getProfile(@PathVariable Long id) {
        StudentProfile profile = service.getProfile(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllProfiles() {
        List<StudentProfile> profiles = service.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }
}