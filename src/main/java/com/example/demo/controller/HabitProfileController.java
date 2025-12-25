package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profiles")
public class HabitProfileController {
    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<HabitProfile> createOrUpdateHabitProfile(@PathVariable Long studentId,
                                                                   @RequestBody HabitProfileDto dto) {
        HabitProfile habit = service.createOrUpdate(studentId, dto);
        return ResponseEntity.ok(habit);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<HabitProfile> getHabitProfile(@PathVariable Long studentId) {
        HabitProfile habit = service.getForStudent(studentId);
        return ResponseEntity.ok(habit);
    }
}