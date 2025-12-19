package com.example.demo.service;

import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public StudentProfile createStudent(StudentProfile profile) {
        if (studentProfileRepository.findByStudentId(profile.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("studentId exists");
        }
        
        profile.setActive(true);
        profile.setCreatedAt(java.time.LocalDateTime.now());

        return studentProfileRepository.save(profile);
    }

    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    public StudentProfile findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile profile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        profile.setActive(active);
        return studentProfileRepository.save(profile);
    }
}


the above is studentprofileservice

package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    // Constructor Injection (MANDATORY)
    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {

        if (studentProfileRepository.findByStudentId(profile.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("studentId exists");
        }

        profile.setCreatedAt(LocalDateTime.now());
        profile.setActive(true);

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile student = getStudentById(id);
        student.setActive(active);
        return studentProfileRepository.save(student);
    }
}


the above is student profileimpl code

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


ROOM ASSIGNMENT SERVICE IS ABOVE


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

ROOMASSIGNMENTIMPL IS ABOVE

package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;
import java.util.List;

public interface MatchAttemptService {

    MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt);

    List<MatchAttemptRecord> getAttemptsByStudent(Long studentId);

    MatchAttemptRecord updateAttemptStatus(Long attemptId, String status);

    List<MatchAttemptRecord> getAllMatchAttempts();
}


MATCHATTEMPT SERVICE