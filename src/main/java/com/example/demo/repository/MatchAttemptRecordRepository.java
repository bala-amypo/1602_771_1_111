package com.example.demo.repository;

import com.example.demo.model.MatchAttemptRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchAttemptRecordRepository
        extends JpaRepository<MatchAttemptRecord, Long> {
}
