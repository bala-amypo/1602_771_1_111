package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profiles")
public class HabitProfileController {

    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    @PostMapping
    public HabitProfile saveHabit(@RequestBody HabitProfile habitProfile) {
        return service.saveOrUpdate(habitProfile);
    }

    @GetMapping("/student/{studentId}")
    public HabitProfile getByStudent(@PathVariable Long studentId) {
        return service.findByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public HabitProfile getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<HabitProfile> getAll() {
        return service.findAll();
    }
}
