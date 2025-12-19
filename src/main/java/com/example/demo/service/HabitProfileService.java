package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import java.util.List;

public interface HabitProfileService {

    HabitProfile createOrUpdateHabit(HabitProfile habit);

    HabitProfile getHabitByStudent(Long studentId);

    List<HabitProfile> getAllHabitProfiles();

    HabitProfile getHabitById(Long id);
}
