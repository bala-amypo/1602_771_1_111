package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;
import java.util.List;

public interface CompatibilityScoreService {

    CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId);

    CompatibilityScoreRecord getScoreById(Long id);

    List<CompatibilityScoreRecord> getScoresForStudent(Long studentId);

    List<CompatibilityScoreRecord> getAllScores();
}
