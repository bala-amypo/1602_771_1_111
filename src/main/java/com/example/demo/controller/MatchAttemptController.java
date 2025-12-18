package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempt")
public class MatchAttemptController {

    private final MatchAttemptService matchAttemptService;

    public MatchAttemptController(MatchAttemptService matchAttemptService) {
        this.matchAttemptService = matchAttemptService;
    }

    @PostMapping
    public MatchAttemptRecord logAttempt(@RequestBody MatchAttemptRecord attempt) {
        return matchAttemptService.logMatchAttempt(attempt);
    }

    @GetMapping("/student/{studentId}")
    public List<MatchAttemptRecord> getAttemptsByStudent(
            @PathVariable Long studentId) {
        return matchAttemptService.getAttemptsByStudent(studentId);
    }

    @PutMapping("/{id}/status")
    public MatchAttemptRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return matchAttemptService.updateAttemptStatus(id, status);
    }

    @GetMapping
    public List<MatchAttemptRecord> getAllAttempts() {
        return matchAttemptService.getAllMatchAttempts();
    }
}
