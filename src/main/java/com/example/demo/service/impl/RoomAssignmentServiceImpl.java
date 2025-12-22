@Override
public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

    StudentProfile studentA =
            studentProfileRepository.findById(assignment.getStudentAId()).orElse(null);

    StudentProfile studentB =
            studentProfileRepository.findById(assignment.getStudentBId()).orElse(null);

    // Validate ONLY if students exist (portal does not preload students)
    if (studentA != null && studentB != null) {
        if (!studentA.getActive() || !studentB.getActive()) {
            throw new IllegalArgumentException("both students must be active");
        }
    }

    assignment.setAssignedAt(LocalDateTime.now());
    assignment.setStatus("ACTIVE");

    return roomAssignmentRepository.save(assignment);
}
