package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;

public interface RoomAssignmentService {

    RoomAssignmentRecord assignRoom(String roomNumber, Long studentAId, Long studentBId);
}
