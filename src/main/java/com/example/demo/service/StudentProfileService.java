package com.example.demo.service;

import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileService(StudentProfileRepository repository) {
        this.repository = repository;
    }

    public StudentProfile createStudent(StudentProfile profile) {
        if (repository.findByStudentId(profile.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("studentId exists");
        }
        profile.setCreatedAt(LocalDateTime.now());
        return repository.save(profile);
    }

    public StudentProfile getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<StudentProfile> getAllStudents() {
        return repository.findAll();
    }

    public StudentProfile findByStudentId(String studentId) {
        return repository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile student = getStudentById(id);
        student.setActive(active);
        return repository.save(student);
    }
}
