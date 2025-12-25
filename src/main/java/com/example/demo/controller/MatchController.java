package com.example.demo.controller;

import com.example.demo.model.MatchResult;
import com.example.demo.service.MatchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches")
public class MatchController {
    private final MatchService service;

    public MatchController(MatchService service) {
        this.service = service;
    }

    @PostMapping("/compute")
    public ResponseEntity<MatchResult> computeMatch(@RequestBody Map<String, Long> request) {
        Long studentAId = request.get("studentAId");
        Long studentBId = request.get("studentBId");
        MatchResult result = service.computeMatch(studentAId, studentBId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MatchResult>> getMatchesForStudent(@PathVariable Long studentId) {
        List<MatchResult> matches = service.getMatchesFor(studentId);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResult> getMatchById(@PathVariable Long id) {
        MatchResult match = service.getById(id);
        return ResponseEntity.ok(match);
    }
}