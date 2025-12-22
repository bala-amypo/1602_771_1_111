package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository matchAttemptRepository;
    private final CompatibilityScoreRecordRepository scoreRepository;

    public MatchAttemptServiceImpl(
            MatchAttemptRecordRepository matchAttemptRepository,
            CompatibilityScoreRecordRepository scoreRepository) {
        this.matchAttemptRepository = matchAttemptRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {

        CompatibilityScoreRecord score =
                scoreRepository.findById(attempt.getResultScoreId()).orElse(null);

        if (score != null) {
            if (score.getScore() >= 60) {
                attempt.setStatus("MATCHED");
            } else {
                attempt.setStatus("NOT_COMPATIBLE");
            }
        } else {
            attempt.setStatus("PENDING_REVIEW");
        }

        attempt.setAttemptedAt(LocalDateTime.now());
        return matchAttemptRepository.save(attempt);
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return matchAttemptRepository
                .findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
    public MatchAttemptRecord updateAttemptStatus(Long attemptId, String status) {

        MatchAttemptRecord record = matchAttemptRepository
                .findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        record.setStatus(status);
        return matchAttemptRepository.save(record);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return matchAttemptRepository.findAll();
    }
}
