package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_assignments")
public class RoomAssignmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Long studentAId;

    @Column(nullable = false)
    private Long studentBId;

    private LocalDateTime assignedAt;

    @Column(nullable = false)
    private String status;

    protected RoomAssignmentRecord() {
    }

    public RoomAssignmentRecord(String roomNumber,
                                Long studentAId,
                                Long studentBId,
                                boolean studentAActive,
                                boolean studentBActive) {

        if (!studentAActive || !studentBActive) {
            throw new IllegalArgumentException("both students must be active");
        }

        this.roomNumber = roomNumber;
        this.studentAId = studentAId;
        this.studentBId = studentBId;
        this.status = "ACTIVE";
        this.assignedAt = LocalDateTime.now();
    }

    public void completeAssignment() {
        this.status = "COMPLETED";
    }

    public void cancelAssignment() {
        this.status = "CANCELLED";
    }

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Long getStudentAId() {
        return studentAId;
    }

    public Long getStudentBId() {
        return studentBId;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public String getStatus() {
        return status;
    }
}
