package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Room Assignment")
public class RoomAssignmentController {

    private final RoomAssignmentService roomAssignmentService;

    public RoomAssignmentController(RoomAssignmentService roomAssignmentService) {
        this.roomAssignmentService = roomAssignmentService;
    }

    @PostMapping
    public RoomAssignmentRecord assignRoom(@RequestBody RoomAssignmentRecord assignment) {
        return roomAssignmentService.assignRoom(assignment);
    }

    @GetMapping("/{id}")
    public RoomAssignmentRecord getAssignmentById(@PathVariable Long id) {
        return roomAssignmentService.getAssignmentById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<RoomAssignmentRecord> getAssignmentsByStudent(
            @PathVariable Long studentId) {
        return roomAssignmentService.getAssignmentsByStudent(studentId);
    }

    @GetMapping
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomAssignmentService.getAllAssignments();
    }

    @PutMapping("/{id}/status")
    public RoomAssignmentRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return roomAssignmentService.updateStatus(id, status);
    }
}
