package com.example.demo.service.impl;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.NoiseTolerance;
import com.example.demo.model.StudentProfile;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;
    private final UserAccountRepository userAccountRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository,
                                    UserAccountRepository userAccountRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public StudentProfile createProfile(StudentProfileDto dto, Long userId) {
        UserAccount user = userAccountRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (studentProfileRepository.findByUserAccountId(userId).isPresent()) {
            throw new IllegalArgumentException("Profile already exists for this user");
        }

        if (dto.getAge() == null || dto.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }

        if (dto.getYearOfStudy() == null || dto.getYearOfStudy() <= 0) {
            throw new IllegalArgumentException("Year of study must be positive");
        }

        if (dto.getRoomTypePreference() != null) {
            String roomType = dto.getRoomTypePreference().toUpperCase();
            if (!roomType.equals("SINGLE") && !roomType.equals("DOUBLE") && !roomType.equals("TRIPLE")) {
                throw new IllegalArgumentException("Invalid room type preference");
            }
        }

        StudentProfile profile = new StudentProfile();
        profile.setUserAccount(user);
        profile.setName(dto.getName());
        profile.setAge(dto.getAge());
        profile.setCourse(dto.getCourse());
        profile.setYearOfStudy(dto.getYearOfStudy());
        profile.setGender(dto.getGender());
        profile.setRoomTypePreference(dto.getRoomTypePreference());
        profile.setSleepTime(dto.getSleepTime());
        profile.setWakeTime(dto.getWakeTime());
        profile.setSmoking(dto.getSmoking());
        profile.setDrinking(dto.getDrinking());
        
        if (dto.getNoiseTolerance() != null) {
            profile.setNoiseTolerance(NoiseTolerance.valueOf(dto.getNoiseTolerance()));
        }
        
        profile.setStudyTime(dto.getStudyTime());

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile updateProfile(Long id, StudentProfileDto dto) {
        StudentProfile profile = studentProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

        if (dto.getAge() != null && dto.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }

        if (dto.getYearOfStudy() != null && dto.getYearOfStudy() <= 0) {
            throw new IllegalArgumentException("Year of study must be positive");
        }

        if (dto.getRoomTypePreference() != null) {
            String roomType = dto.getRoomTypePreference().toUpperCase();
            if (!roomType.equals("SINGLE") && !roomType.equals("DOUBLE") && !roomType.equals("TRIPLE")) {
                throw new IllegalArgumentException("Invalid room type preference");
            }
        }

        if (dto.getName() != null) profile.setName(dto.getName());
        if (dto.getAge() != null) profile.setAge(dto.getAge());
        if (dto.getCourse() != null) profile.setCourse(dto.getCourse());
        if (dto.getYearOfStudy() != null) profile.setYearOfStudy(dto.getYearOfStudy());
        if (dto.getGender() != null) profile.setGender(dto.getGender());
        if (dto.getRoomTypePreference() != null) profile.setRoomTypePreference(dto.getRoomTypePreference());
        if (dto.getSleepTime() != null) profile.setSleepTime(dto.getSleepTime());
        if (dto.getWakeTime() != null) profile.setWakeTime(dto.getWakeTime());
        if (dto.getSmoking() != null) profile.setSmoking(dto.getSmoking());
        if (dto.getDrinking() != null) profile.setDrinking(dto.getDrinking());
        if (dto.getNoiseTolerance() != null) {
            profile.setNoiseTolerance(NoiseTolerance.valueOf(dto.getNoiseTolerance()));
        }
        if (dto.getStudyTime() != null) profile.setStudyTime(dto.getStudyTime());

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfile(Long id) {
        return studentProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentProfileRepository.findAll();
    }
}