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

    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo,
                                         HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long a, Long b) {
        if (a.equals(b))
            throw new IllegalArgumentException("same student");

        HabitProfile h1 = habitRepo.findByStudentId(a)
                .orElseThrow(() -> new RuntimeException("not found"));
        HabitProfile h2 = habitRepo.findByStudentId(b)
                .orElseThrow(() -> new RuntimeException("not found"));

        CompatibilityScoreRecord rec =
                scoreRepo.findByStudentAIdAndStudentBId(a, b)
                        .orElse(new CompatibilityScoreRecord());

        rec.setStudentAId(a);
        rec.setStudentBId(b);
        rec.setScore(80.0);
        rec.setComputedAt(LocalDateTime.now());

        return scoreRepo.save(rec);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long id) {
        return scoreRepo.findByStudentAIdOrStudentBId(id, id);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
