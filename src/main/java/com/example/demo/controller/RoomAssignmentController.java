// RoomAssignmentController.java
package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomAssignmentController {

    private final RoomAssignmentService assignmentService;

    public RoomAssignmentController(RoomAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<RoomAssignmentRecord> assign(@RequestBody RoomAssignmentRecord record) {
        return ResponseEntity.ok(assignmentService.assignRoom(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomAssignmentRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
}
