package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profile")
public class HabitProfileController {

    private final HabitProfileService habitProfileService;

    public HabitProfileController(HabitProfileService habitProfileService) {
        this.habitProfileService = habitProfileService;
    }

    @PostMapping
    public HabitProfile createOrUpdateHabit(@RequestBody HabitProfile habitProfile) {
        return habitProfileService.createOrUpdateHabit(habitProfile);
    }

    @GetMapping("/student/{studentId}")
    public HabitProfile getHabitByStudent(@PathVariable Long studentId) {
        return habitProfileService.getHabitByStudent(studentId);
    }

    @GetMapping
    public List<HabitProfile> getAllHabits() {
        return habitProfileService.getAllHabitProfiles();
    }

    @GetMapping("/{id}")
    public HabitProfile getHabitById(@PathVariable Long id) {
        return habitProfileService.getHabitById(id);
    }
}
