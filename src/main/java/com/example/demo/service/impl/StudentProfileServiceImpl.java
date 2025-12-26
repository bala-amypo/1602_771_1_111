package com.example.demo.service.impl;

import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repo;

    public StudentProfileServiceImpl(StudentProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile s) {
        if (repo.findByStudentId(s.getStudentId()).isPresent())
            throw new IllegalArgumentException("studentId exists");
        if (repo.findByEmail(s.getEmail()).isPresent())
            throw new IllegalArgumentException("email exists");
        return repo.save(s);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile s = getStudentById(id);
        s.setActive(active);
        return repo.save(s);
    }

    @Override
    public Optional<StudentProfile> findByStudentId(String studentId) {
        return repo.findByStudentId(studentId);
    }
}
