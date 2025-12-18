package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepository;
    private final HabitProfileRepository habitRepository;

    // ✅ Constructor injection (REQUIRED by test cases)
    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepository,
            HabitProfileRepository habitRepository) {
        this.scoreRepository = scoreRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        // 🔴 REQUIRED validation
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }

        HabitProfile a = habitRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        HabitProfile b = habitRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        double score = 0.0;

        if (a.getSleepSchedule() == b.getSleepSchedule()) score += 20;
        if (a.getCleanlinessLevel() == b.getCleanlinessLevel()) score += 20;
        if (a.getNoiseTolerance() == b.getNoiseTolerance()) score += 20;
        if (a.getSocialPreference() == b.getSocialPreference()) score += 20;

        int diff = Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay());
        score += Math.max(0, 20 - diff);

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(score);
        record.setComputedAt(LocalDateTime.now());

        if (score >= 80) record.setCompatibilityLevel("EXCELLENT");
        else if (score >= 60) record.setCompatibilityLevel("HIGH");
        else if (score >= 40) record.setCompatibilityLevel("MEDIUM");
        else record.setCompatibilityLevel("LOW");

        return scoreRepository.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}
