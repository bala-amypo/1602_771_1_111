// CompatibilityScoreServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;

import java.time.LocalDateTime;
import java.util.List;

public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo,
                                         HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }

        HabitProfile a = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() -> new RuntimeException("habit not found"));
        HabitProfile b = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() -> new RuntimeException("habit not found"));

        double score = 100.0;

        if (a.getSleepSchedule() != b.getSleepSchedule()) score -= 15;
        if (a.getCleanlinessLevel() != b.getCleanlinessLevel()) score -= 20;
        if (a.getNoiseTolerance() != b.getNoiseTolerance()) score -= 15;
        if (a.getSocialPreference() != b.getSocialPreference()) score -= 10;

        if (a.getStudyHoursPerDay() != null && b.getStudyHoursPerDay() != null) {
            score -= Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay()) * 2;
        }

        if (score < 0) score = 0;
        if (score > 100) score = 100;

        CompatibilityScoreRecord rec = scoreRepo
                .findByStudentAIdAndStudentBId(studentAId, studentBId)
                .orElse(new CompatibilityScoreRecord());

        rec.setStudentAId(studentAId);
        rec.setStudentBId(studentBId);
        rec.setScore(score);
        rec.setComputedAt(LocalDateTime.now());

        if (score >= 90) rec.setCompatibilityLevel(CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT);
        else if (score >= 70) rec.setCompatibilityLevel(CompatibilityScoreRecord.CompatibilityLevel.GOOD);
        else if (score >= 40) rec.setCompatibilityLevel(CompatibilityScoreRecord.CompatibilityLevel.AVERAGE);
        else rec.setCompatibilityLevel(CompatibilityScoreRecord.CompatibilityLevel.POOR);

        return scoreRepo.save(rec);
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("score not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
