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

    public RoomAssignmentRecord assignRoom(R
