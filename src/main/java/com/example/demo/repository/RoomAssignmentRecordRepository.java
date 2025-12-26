// RoomAssignmentRecordRepository.java
package com.example.demo.repository;

import com.example.demo.model.RoomAssignmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomAssignmentRecordRepository
        extends JpaRepository<RoomAssignmentRecord, Long> {

    List<RoomAssignmentRecord> findByStudentAIdOrStudentBId(Long studentAId, Long studentBId);
}
