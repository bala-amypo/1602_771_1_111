// RoomAssignmentService.java
package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;

import java.util.List;

public interface RoomAssignmentService {

    RoomAssignmentRecord assignRoom(RoomAssignmentRecord record);

    RoomAssignmentRecord updateStatus(Long id, String status);

    List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId);

    List<RoomAssignmentRecord> getAllAssignments();

    RoomAssignmentRecord getAssignmentById(Long id);
}
