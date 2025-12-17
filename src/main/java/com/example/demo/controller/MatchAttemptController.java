package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts")
public class MatchAttemptController {

    private final MatchAttemptService service;

    public MatchAttemptController(MatchAttemptService service) {
        this.service = service;
    }

    @PostMapping("/{initiatorStudentId}/{candidateStudentId}")
    public MatchAttemptRecord attemptMatch(
            @PathVariable Long initiatorStudentId,
            @PathVariable Long candidateStudentId) {

        return service.attemptMatch(initiatorStudentId, candidateStudentId);
    }
}
