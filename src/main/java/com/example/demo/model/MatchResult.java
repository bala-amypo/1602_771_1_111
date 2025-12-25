package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_results")
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_a_id")
    private StudentProfile studentA;
    
    @ManyToOne
    @JoinColumn(name = "student_b_id")
    private StudentProfile studentB;
    
    private Double score;
    private String reasonSummary;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public MatchResult() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public StudentProfile getStudentA() { return studentA; }
    public void setStudentA(StudentProfile studentA) { this.studentA = studentA; }
    
    public StudentProfile getStudentB() { return studentB; }
    public void setStudentB(StudentProfile studentB) { this.studentB = studentB; }
    
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    
    public String getReasonSummary() { return reasonSummary; }
    public void setReasonSummary(String reasonSummary) { this.reasonSummary = reasonSummary; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}