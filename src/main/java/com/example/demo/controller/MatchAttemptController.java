// MatchAttemptController.java
package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match-attempts")
public class MatchAttemptController {

    private final MatchAttemptService attemptService;

    public MatchAttemptController(MatchAttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping
    public ResponseEntity<MatchAttemptRecord> log(@RequestBody MatchAttemptRecord attempt) {
        return ResponseEntity.ok(attemptService.logMatchAttempt(attempt));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(attemptService.getAllMatchAttempts());
    }
}
