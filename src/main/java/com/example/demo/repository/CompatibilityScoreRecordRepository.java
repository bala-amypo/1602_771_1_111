package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CompatibilityScoreRecordRepository
        extends JpaRepository<CompatibilityScoreRecord, Long> {

    Optional<CompatibilityScoreRecord> findByStudentAIdAndStudentBId(Long id1, Long id2);

    List<CompatibilityScoreRecord> findByStudentAIdOrStudentBId(Long id1, Long id2);
}
 this is CompatibilityScoreRecordRepository.java

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.HabitProfile;
import java.util.Optional;

public interface HabitProfileRepository extends JpaRepository<HabitProfile, Long> {

    Optional<HabitProfile> findByStudentId(Long studentId);
}
 thsi is 
