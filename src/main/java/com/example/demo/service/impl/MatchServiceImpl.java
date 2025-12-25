package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.MatchResult;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.MatchResultRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.MatchService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchResultRepository matchResultRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final HabitProfileRepository habitProfileRepository;

    public MatchServiceImpl(MatchResultRepository matchResultRepository,
                           StudentProfileRepository studentProfileRepository,
                           HabitProfileRepository habitProfileRepository) {
        this.matchResultRepository = matchResultRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.habitProfileRepository = habitProfileRepository;
    }

    @Override
    public MatchResult computeMatch(Long studentAId, Long studentBId) {
        StudentProfile studentA = studentProfileRepository.findById(studentAId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        StudentProfile studentB = studentProfileRepository.findById(studentBId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        HabitProfile habitA = habitProfileRepository.findByStudentId(studentAId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        HabitProfile habitB = habitProfileRepository.findByStudentId(studentBId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double score = calculateCompatibility(habitA, habitB);

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0-100");
        }

        MatchResult result = new MatchResult();
        result.setStudentA(studentA);
        result.setStudentB(studentB);
        result.setScore(score);
        result.setReasonSummary("Compatibility based on habits and preferences");

        return matchResultRepository.save(result);
    }

    private double calculateCompatibility(HabitProfile a, HabitProfile b) {
        double score = 0.0;

        if (a.getSleepTime() != null && a.getSleepTime().equals(b.getSleepTime())) score += 15;
        if (a.getWakeTime() != null && a.getWakeTime().equals(b.getWakeTime())) score += 15;
        if (a.getCleanlinessLevel() == b.getCleanlinessLevel()) score += 20;
        if (a.getNoisePreference() == b.getNoisePreference()) score += 20;
        if (a.getSocialPreference() == b.getSocialPreference()) score += 15;
        if (a.getSmoking() != null && a.getSmoking().equals(b.getSmoking())) score += 10;
        if (a.getDrinking() != null && a.getDrinking().equals(b.getDrinking())) score += 5;

        return Math.min(score, 100.0);
    }

    @Override
    public List<MatchResult> getMatchesFor(Long studentId) {
        return matchResultRepository.findByStudentAIdOrStudentBIdOrderByScoreDesc(studentId, studentId);
    }

    @Override
    public MatchResult getById(Long id) {
        return matchResultRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Match not found"));
    }
}