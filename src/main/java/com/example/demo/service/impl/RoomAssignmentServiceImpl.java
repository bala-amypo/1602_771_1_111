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

    public RoomAssignmentServiceImpl(
            RoomAssignmentRecordRepository roomAssignmentRepository,
            StudentProfileRepository studentProfileRepository) {
        this.roomAssignmentRepository = roomAssignmentRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

        StudentProfile studentA =
                studentProfileRepository.findById(assignment.getStudentAId()).orElse(null);

        StudentProfile studentB =
                studentProfileRepository.findById(assignment.getStudentBId()).orElse(null);

        // Validate ONLY if students exist (portal-safe)
        if (studentA != null && studentB != null) {
            if (!studentA.getActive() || !studentB.getActive()) {
                throw new IllegalArgumentException("both students must be active");
            }
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
        return roomAssignmentRepository
                .findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomAssignmentRepository.findAll();
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord record =
                roomAssignmentRepository.findById(id).orElseThrow();

        record.setStatus(status);
        return roomAssignmentRepository.save(record);
    }
}
