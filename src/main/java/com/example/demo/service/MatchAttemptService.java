package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchAttemptService {

    private final MatchAttemptRecordRepository attemptRepository;
    private final CompatibilityScoreRecordRepository scoreRepository;

    public MatchAttemptService(
            MatchAttemptRecordRepository attemptRepository,
            CompatibilityScoreRecordRepository scoreRepository) {
        this.attemptRepository = attemptRepository;
        this.scoreRepository = scoreRepository;
    }

    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {

        // ensure score exists
        scoreRepository.findById(attempt.getResultScoreId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        attempt.setAttemptedAt(LocalDateTime.now());
        return attemptRepository.save(attempt);
    }

    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return attemptRepository.findByInitiatorStudentIdOrCandidateStudentId(
                studentId, studentId);
    }

    public MatchAttemptRecord updateAttemptStatus(Long attemptId, String status) {
        MatchAttemptRecord record = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        record.setStatus(status);
        return attemptRepository.save(record);
    }

    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return attemptRepository.findAll();
    }
}
