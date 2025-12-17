package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempts")
public class MatchAttemptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;

    private Long resultScoreId;

    @Column(nullable = false)
    private String status; 
    private LocalDateTime attemptedAt;

    protected MatchAttemptRecord() {
        
    }

    public MatchAttemptRecord(Long initiatorStudentId, Long candidateStudentId) {

        if (initiatorStudentId.equals(candidateStudentId)) {
            throw new IllegalArgumentException("same student");
        }

        this.initiatorStudentId = initiatorStudentId;
        this.candidateStudentId = candidateStudentId;
        this.status = "PENDING_REVIEW";
        this.attemptedAt = LocalDateTime.now();
    }

   

    public void markMatched(Long scoreId) {
        this.resultScoreId = scoreId;
        this.status = "MATCHED";
    }

    public void markRejected(Long scoreId) {
        this.resultScoreId = scoreId;
        this.status = "NOT_COMPATIBLE";
    }

    

    public Long getId() {
        return id;
    }

    public Long getInitiatorStudentId() {
        return initiatorStudentId;
    }

    public Long getCandidateStudentId() {
        return candidateStudentId;
    }

    public Long getResultScoreId() {
        return resultScoreId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }
}
