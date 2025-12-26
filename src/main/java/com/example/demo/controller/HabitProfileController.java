// HabitProfileController.java
package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdate(@RequestBody HabitProfile habit) {
        return ResponseEntity.ok(habitService.createOrUpdateHabit(habit));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<HabitProfile> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(habitService.getHabitByStudent(studentId));
    }
}
