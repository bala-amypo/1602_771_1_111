package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository roomAssignmentRepository;
    private final StudentProfileRepository studentProfileRepository;

    // ✅ Constructor injection (MANDATORY for test cases)
    public RoomAssignmentServiceImpl(
            RoomAssignmentRecordRepository roomAssignmentRepository,
            StudentProfileRepository studentProfileRepository) {
        this.roomAssignmentRepository = roomAssignmentRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

        StudentProfile studentA = studentProfileRepository
                .findById(assignment.getStudentAId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        StudentProfile studentB = studentProfileRepository
                .findById(assignment.getStudentBId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        // 🔴 REQUIRED validation
        if (!studentA.getActive() || !studentB.getActive()) {
            throw new IllegalArgumentException("both students must be active");
        }

        assignment.setAssignedAt(LocalDateTime.now());
        assignment.setStatus("ACTIVE");

        return roomAssignmentRepository.save(assignment);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return roomAssignmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return roomAssignmentRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomAssignmentRepository.findAll();
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord record = roomAssignmentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        record.setStatus(status);
        return roomAssignmentRepository.save(record);
    }
}
