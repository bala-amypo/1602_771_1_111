package com.example.demo.repository;

import com.example.demo.model.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {
    List<MatchResult> findByStudentAIdOrStudentBIdOrderByScoreDesc(Long studentAId, Long studentBId);
    Optional<MatchResult> findFirstByStudentAIdAndStudentBIdOrderByCreatedAtDesc(Long studentAId, Long studentBId);
}