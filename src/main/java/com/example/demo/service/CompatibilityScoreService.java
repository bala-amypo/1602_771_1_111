package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepository;
    private final HabitProfileRepository habitProfileRepository;

    public CompatibilityScoreService(
            CompatibilityScoreRecordRepository scoreRepository,
            HabitProfileRepository habitProfileRepository) {
        this.scoreRepository = scoreRepository;
        this.habitProfileRepository = habitProfileRepository;
    }

    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }

        HabitProfile habitA = habitProfileRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        HabitProfile habitB = habitProfileRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        double score = 0.0;

        if (habitA.getSleepSchedule() != null &&
                habitA.getSleepSchedule().equals(habitB.getSleepSchedule())) {
            score += 20;
        }

        if (habitA.getCleanlinessLevel() != null &&
                habitA.getCleanlinessLevel().equals(habitB.getCleanlinessLevel())) {
            score += 20;
        }

        if (habitA.getNoiseTolerance() != null &&
                habitA.getNoiseTolerance().equals(habitB.getNoiseTolerance())) {
            score += 20;
        }

        if (habitA.getSocialPreference() != null &&
                habitA.getSocialPreference().equals(habitB.getSocialPreference())) {
            score += 20;
        }

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(score);
        record.setCompatibilityLevel(score >= 60 ? "HIGH" : "LOW");
        record.setComputedAt(LocalDateTime.now());
        record.setDetailsJson("{}");

        return scoreRepository.save(record);
    }

    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}
