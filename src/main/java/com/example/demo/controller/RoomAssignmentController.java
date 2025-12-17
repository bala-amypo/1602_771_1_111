package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room-assignments")
@Tag(name = "Room Assignments")
public class RoomAssignmentController {

    private final RoomAssignmentService service;

    public RoomAssignmentController(RoomAssignmentService service) {
        this.service = service;
    }

    @PostMapping("/{roomNumber}/{studentAId}/{studentBId}")
    public RoomAssignmentRecord assignRoom(
            @PathVariable String roomNumber,
            @PathVariable Long studentAId,
            @PathVariable Long studentBId) {

        return service.assignRoom(roomNumber, studentAId, studentBId);
    }
}
