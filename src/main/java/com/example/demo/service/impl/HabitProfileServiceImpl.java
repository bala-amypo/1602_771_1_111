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
