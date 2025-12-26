// RoomAssignmentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;

import java.util.List;

public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository roomRepo;
    private final StudentProfileRepository studentRepo;

    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository roomRepo,
                                     StudentProfileRepository studentRepo) {
        this.roomRepo = roomRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord record) {
        StudentProfile a = studentRepo.findById(record.getStudentAId())
                .orElseThrow(() -> new RuntimeException("student not found"));
        StudentProfile b = studentRepo.findById(record.getStudentBId())
                .orElseThrow(() -> new RuntimeException("student not found"));

        if (!Boolean.TRUE.equals(a.getActive()) || !Boolean.TRUE.equals(b.getActive())) {
            throw new IllegalArgumentException("both students must be active");
        }

        record.setStatus(RoomAssignmentRecord.Status.ACTIVE);
        return roomRepo.save(record);
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord r = roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("room assignment not found"));
        r.setStatus(RoomAssignmentRecord.Status.valueOf(status));
        return roomRepo.save(r);
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return roomRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomRepo.findAll();
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("room assignment not found"));
    }
}
