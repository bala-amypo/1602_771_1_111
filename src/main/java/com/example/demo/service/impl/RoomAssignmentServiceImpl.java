package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository repo;
    private final StudentProfileRepository studentRepo;

    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository repo,
                                     StudentProfileRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord r) {
        StudentProfile a = studentRepo.findById(r.getStudentAId())
                .orElseThrow(() -> new RuntimeException("not found"));
        StudentProfile b = studentRepo.findById(r.getStudentBId())
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!a.getActive() || !b.getActive())
            throw new IllegalArgumentException("both students must be active");

        r.setStatus(RoomAssignmentRecord.Status.ACTIVE);
        return repo.save(r);
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        r.setStatus(RoomAssignmentRecord.Status.valueOf(status));
        return repo.save(r);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long id) {
        return repo.findByStudentAIdOrStudentBId(id, id);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return repo.findAll();
    }
}
