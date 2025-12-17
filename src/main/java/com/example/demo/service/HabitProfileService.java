package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;

    public HabitProfileService(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    public HabitProfile createOrUpdateHabit(HabitProfile habitProfile) {
        return null;
    }

    public HabitProfile getHabitByStudent(Long studentId) {
        return null;
    }

    public List<HabitProfile> getAllHabitProfiles() {
        return null;
    }

    public HabitProfile getHabitById(Long id) {
        return null;
    }
}
