package com.example.demo.service;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import java.util.List;

public interface StudentProfileService {
    StudentProfile createProfile(StudentProfileDto dto, Long userId);
    StudentProfile updateProfile(Long id, StudentProfileDto dto);
    StudentProfile getProfile(Long id);
    List<StudentProfile> getAllProfiles();
}