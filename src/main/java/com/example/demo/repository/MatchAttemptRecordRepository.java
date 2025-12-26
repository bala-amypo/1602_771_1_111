// MatchAttemptRecordRepository.java
package com.example.demo.repository;

import com.example.demo.model.MatchAttemptRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchAttemptRecordRepository
        extends JpaRepository<MatchAttemptRecord, Long> {

    List<MatchAttemptRecord> findByInitiatorStudentIdOrCandidateStudentId(
            Long initiatorStudentId, Long candidateStudentId
    );
}
