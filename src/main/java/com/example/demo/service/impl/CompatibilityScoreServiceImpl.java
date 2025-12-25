package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepository;
    private final HabitProfileRepository habitRepository;

    // ✅ Constructor injection (REQUIRED by test cases)
    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepository,
            HabitProfileRepository habitRepository) {
        this.scoreRepository = scoreRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        // 🔴 REQUIRED validation
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }

        HabitProfile a = habitRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        HabitProfile b = habitRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        double score = 0.0;

        if (a.getSleepSchedule() == b.getSleepSchedule()) score += 20;
        if (a.getCleanlinessLevel() == b.getCleanlinessLevel()) score += 20;
        if (a.getNoiseTolerance() == b.getNoiseTolerance()) score += 20;
        if (a.getSocialPreference() == b.getSocialPreference()) score += 20;

        int diff = Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay());
        score += Math.max(0, 20 - diff);

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(score);
        record.setComputedAt(LocalDateTime.now());

        if (score >= 80) record.setCompatibilityLevel("EXCELLENT");
        else if (score >= 60) record.setCompatibilityLevel("HIGH");
        else if (score >= 40) record.setCompatibilityLevel("MEDIUM");
        else record.setCompatibilityLevel("LOW");

        return scoreRepository.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}
this is in folder of service with sub folder of impl wiht file name of COmpatibilityScoreServiceIMpl.java

package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;

    // ✅ Constructor Injection (MANDATORY for test cases)
    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {

        if (habit.getStudyHoursPerDay() == null || habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours");
        }

        habit.setUpdatedAt(LocalDateTime.now());
        return habitProfileRepository.save(habit);
    }

    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElse(null);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitProfileRepository.findAll();
    }

    @Override
    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id).orElse(null);
    }
}

this is HabitprofileServiceimpl.java

package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository matchAttemptRepository;
    private final CompatibilityScoreRecordRepository scoreRepository;

    public MatchAttemptServiceImpl(
            MatchAttemptRecordRepository matchAttemptRepository,
            CompatibilityScoreRecordRepository scoreRepository) {
        this.matchAttemptRepository = matchAttemptRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {

        CompatibilityScoreRecord score =
                scoreRepository.findById(attempt.getResultScoreId()).orElse(null);

        if (score != null) {
            if (score.getScore() >= 60) {
                attempt.setStatus("MATCHED");
            } else {
                attempt.setStatus("NOT_COMPATIBLE");
            }
        } else {
            attempt.setStatus("PENDING_REVIEW");
        }

        attempt.setAttemptedAt(LocalDateTime.now());
        return matchAttemptRepository.save(attempt);
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return matchAttemptRepository
                .findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
    public MatchAttemptRecord updateAttemptStatus(Long attemptId, String status) {

        MatchAttemptRecord record = matchAttemptRepository
                .findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        record.setStatus(status);
        return matchAttemptRepository.save(record);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return matchAttemptRepository.findAll();
    }
}

this is match AttemptSErviceimpl.java
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

    public RoomAssignmentServiceImpl(
            RoomAssignmentRecordRepository roomAssignmentRepository,
            StudentProfileRepository studentProfileRepository) {
        this.roomAssignmentRepository = roomAssignmentRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

        StudentProfile studentA =
                studentProfileRepository.findById(assignment.getStudentAId()).orElse(null);

        StudentProfile studentB =
                studentProfileRepository.findById(assignment.getStudentBId()).orElse(null);

        // Validate ONLY if students exist (portal-safe)
        if (studentA != null && studentB != null) {
            if (!studentA.getActive() || !studentB.getActive()) {
                throw new IllegalArgumentException("both students must be active");
            }
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
        return roomAssignmentRepository
                .findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomAssignmentRepository.findAll();
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord record =
                roomAssignmentRepository.findById(id).orElseThrow();

        record.setStatus(status);
        return roomAssignmentRepository.save(record);
    }
}
this is Room assignmrnt.java


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


this is StudentProfileServiceimpl.java

package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;
import java.util.List;

public interface CompatibilityScoreService {

    CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId);

    CompatibilityScoreRecord getScoreById(Long id);

    List<CompatibilityScoreRecord> getScoresForStudent(Long studentId);

    List<CompatibilityScoreRecord> getAllScores();
}
 this is under service folder but outseide the impl folder in file of CompatibilityScoreservice.java

 package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import java.util.List;

public interface HabitProfileService {

    HabitProfile createOrUpdateHabit(HabitProfile habit);

    HabitProfile getHabitByStudent(Long studentId);

    List<HabitProfile> getAllHabitProfiles();

    HabitProfile getHabitById(Long id);
}
 this is habitprofileservice.java

 package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;
import java.util.List;

public interface MatchAttemptService {

    MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt);

    List<MatchAttemptRecord> getAttemptsByStudent(Long studentId);

    MatchAttemptRecord updateAttemptStatus(Long attemptId, String status);

    List<MatchAttemptRecord> getAllMatchAttempts();
}
 this is MatchatttemptService.java
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
 this is Roomassignemntservice/java

 package com.example.demo.service;

import com.example.demo.model.StudentProfile;
import java.util.List;

public interface StudentProfileService {

    StudentProfile createStudent(StudentProfile profile);

    StudentProfile getStudentById(Long id);

    List<StudentProfile> getAllStudents();

    StudentProfile findByStudentId(String studentId);

    StudentProfile updateStudentStatus(Long id, boolean active);
}
 this is studentprofile.java"

 


