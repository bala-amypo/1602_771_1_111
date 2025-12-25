package com.example.demo.service;

import com.example.demo.model.MatchResult;
import java.util.List;

public interface MatchService {
    MatchResult computeMatch(Long studentAId, Long studentBId);
    List<MatchResult> getMatchesFor(Long studentId);
    MatchResult getById(Long id);
}