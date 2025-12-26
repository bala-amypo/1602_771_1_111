// CompatibilityScoreService.java
package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;

import java.util.List;

public interface CompatibilityScoreService {

    CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId);

    List<CompatibilityScoreRecord> getScoresForStudent(Long studentId);

    CompatibilityScoreRecord getScoreById(Long id);

    List<CompatibilityScoreRecord> getAllScores();
}
