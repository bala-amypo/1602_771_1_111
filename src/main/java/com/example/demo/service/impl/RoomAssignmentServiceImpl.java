package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import java.util.List;

public interface RoomAssignmentService {

    RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment);

    RoomAssignmentRecord getAssignmentById(Long id);

    List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId);

    List<RoomAssignmentRecord> getAllAssignments();

    RoomAssignmentRecord updateStatus(Long id, String status);
}
