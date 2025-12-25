
package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Score")
public class CompatibilityScoreController {

    private final CompatibilityScoreService compatibilityScoreService;

    public CompatibilityScoreController(CompatibilityScoreService compatibilityScoreService) {
        this.compatibilityScoreService = compatibilityScoreService;
    }

    @PostMapping("/compute")
    public CompatibilityScoreRecord computeScore(
            @RequestParam Long studentAId,
            @RequestParam Long studentBId) {
        return compatibilityScoreService.computeScore(studentAId, studentBId);
    }

    @GetMapping("/{id}")
    public CompatibilityScoreRecord getScoreById(@PathVariable Long id) {
        return compatibilityScoreService.getScoreById(id);
    }

    // 🔴 PATH FIXED TO MATCH TEST CASES
    @GetMapping("/scores/student/{studentId}")
    public List<CompatibilityScoreRecord> getScoresForStudent(
            @PathVariable Long studentId) {
        return compatibilityScoreService.getScoresForStudent(studentId);
    }

    @GetMapping
    public List<CompatibilityScoreRecord> getAllScores() {
        return compatibilityScoreService.getAllScores();
    }
}
