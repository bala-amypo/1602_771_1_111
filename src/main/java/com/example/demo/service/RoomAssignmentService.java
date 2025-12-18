package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomAssignmentService {

    private final RoomAssignmentRecordRepository assignmentRepository;
    private final StudentProfileRepository studentProfileRepository;

    public RoomAssignmentService(
            RoomAssignmentRecordRepository assignmentRepository,
            StudentProfileRepository studentProfileRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

        StudentProfile studentA = studentProfileRepository.findById(assignment.getStudentAId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        StudentProfile studentB = studentProfileRepository.findById(assignment.getStudentBId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!Boolean.TRUE.equals(studentA.getActive())
                || !Boolean.TRUE.equals(studentB.getActive())) {
            throw new IllegalArgumentException("both students must be active");
        }

        assignment.setAssignedAt(LocalDateTime.now());
        return assignmentRepository.save(assignment);
    }

    public RoomAssignmentRecord getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return assignmentRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    public List<RoomAssignmentRecord> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord record = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        record.setStatus(status);
        return assignmentRepository.save(record);
    }
}
