package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Scores")
public class CompatibilityScoreController {

    private final CompatibilityScoreService service;

    public CompatibilityScoreController(CompatibilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/compute/{studentAId}/{studentBId}")
    public CompatibilityScoreRecord computeScore(
            @PathVariable Long studentAId,
            @PathVariable Long studentBId) {
        return service.compute(studentAId, studentBId);
    }

    @GetMapping("/student/{studentId}")
    public List<CompatibilityScoreRecord> getScoresForStudent(
            @PathVariable Long studentId) {
        return service.findByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public CompatibilityScoreRecord getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<CompatibilityScoreRecord> getAllScores() {
        return service.findAll();
    }
}
