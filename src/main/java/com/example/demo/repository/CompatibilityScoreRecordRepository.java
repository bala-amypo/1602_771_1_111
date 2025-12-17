package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompatibilityScoreRecordRepository
        extends JpaRepository<CompatibilityScoreRecord, Long> {

    Optional<CompatibilityScoreRecord>
    findByStudentAIdAndStudentBId(Long studentAId, Long studentBId);
}
