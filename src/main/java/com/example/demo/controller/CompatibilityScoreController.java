package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                // You need to change the port as per your server
                .servers(List.of(
                        new Server().url("https://9126.pro604cr.amypo.ai")
                ));
        }
}
this is Swaggconfig

package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Score")
public class CompatibilityScoreController {

    private final CompatibilityScoreService compatibilityScoreService;

    public CompatibilityScoreController(CompatibilityScoreService compatibilityScoreService) {
        this.compatibilityScoreService = compatibilityScoreService;
    }

    @PostMapping("/compute")
    public CompatibilityScoreRecord computeScore(
            @RequestParam Long studentAId,
            @RequestParam Long studentBId) {
        return compatibilityScoreService.computeScore(studentAId, studentBId);
    }

    @GetMapping("/{id}")
    public CompatibilityScoreRecord getScoreById(@PathVariable Long id) {
        return compatibilityScoreService.getScoreById(id);
    }

    // 🔴 PATH FIXED TO MATCH TEST CASES
    @GetMapping("/scores/student/{studentId}")
    public List<CompatibilityScoreRecord> getScoresForStudent(
            @PathVariable Long studentId) {
        return compatibilityScoreService.getScoresForStudent(studentId);
    }

    @GetMapping
    public List<CompatibilityScoreRecord> getAllScores() {
        return compatibilityScoreService.getAllScores();
    }
}
this is CompatibilityScoreController.java

package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profile")
public class HabitProfileController {

    private final HabitProfileService habitProfileService;

    public HabitProfileController(HabitProfileService habitProfileService) {
        this.habitProfileService = habitProfileService;
    }

    @PostMapping
    public HabitProfile createOrUpdateHabit(@RequestBody HabitProfile habitProfile) {
        return habitProfileService.createOrUpdateHabit(habitProfile);
    }

    // 🔴 PATH FIXED TO MATCH TEST CASES
    @GetMapping("/student/{studentId}")
    public HabitProfile getHabitByStudent(@PathVariable Long studentId) {
        return habitProfileService.getHabitByStudent(studentId);
    }

    @GetMapping
    public List<HabitProfile> getAllHabits() {
        return habitProfileService.getAllHabitProfiles();
    }

    @GetMapping("/{id}")
    public HabitProfile getHabitById(@PathVariable Long id) {
        return habitProfileService.getHabitById(id);
    }
}
this is habitprofilecontroller.java code
package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempt")
public class MatchAttemptController {

    private final MatchAttemptService matchAttemptService;

    public MatchAttemptController(MatchAttemptService matchAttemptService) {
        this.matchAttemptService = matchAttemptService;
    }

    @PostMapping
    public MatchAttemptRecord logAttempt(@RequestBody MatchAttemptRecord attempt) {
        return matchAttemptService.logMatchAttempt(attempt);
    }

    // 🔴 PATH FIXED TO MATCH TEST CASES
    @GetMapping("/student/{studentId}")
    public List<MatchAttemptRecord> getAttemptsByStudent(
            @PathVariable Long studentId) {
        return matchAttemptService.getAttemptsByStudent(studentId);
    }

    @PutMapping("/{id}/status")
    public MatchAttemptRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return matchAttemptService.updateAttemptStatus(id, status);
    }

    @GetMapping
    public List<MatchAttemptRecord> getAllAttempts() {
        return matchAttemptService.getAllMatchAttempts();
    }
}
this is matchattemptcode.java
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

    // 🔴 PATH FIXED TO MATCH TEST CASES
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
this is RoomAssignmentController.java

package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profile API")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile createStudent(@RequestBody StudentProfile profile) {
        return service.createStudent(profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @GetMapping
    public List<StudentProfile> getAllStudents() {
        return service.getAllStudents();
    }

    @PutMapping("/{id}/status")
    public StudentProfile updateStudentStatus(@PathVariable Long id,
                                              @RequestParam boolean active) {
        return service.updateStudentStatus(id, active);
    }

    @GetMapping("/lookup/{studentId}")
    public StudentProfile findByStudentId(@PathVariable String studentId) {
        return service.findByStudentId(studentId);
    }
}
this is studdentProfileCOntroller.java code