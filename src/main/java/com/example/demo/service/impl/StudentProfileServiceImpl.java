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
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile student = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setActive(active);
        return studentProfileRepository.save(student);
    }
}
