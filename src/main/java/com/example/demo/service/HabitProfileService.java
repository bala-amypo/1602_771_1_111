package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;

    public HabitProfileService(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    public HabitProfile createOrUpdateHabit(HabitProfile habitProfile) {

        if (habitProfile.getStudyHoursPerDay() != null &&
                habitProfile.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours");
        }

        habitProfile.setUpdatedAt(LocalDateTime.now());
        return habitProfileRepository.save(habitProfile);
    }

    public HabitProfile getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<HabitProfile> getAllHabitProfiles() {
        return habitProfileRepository.findAll();
    }

    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
