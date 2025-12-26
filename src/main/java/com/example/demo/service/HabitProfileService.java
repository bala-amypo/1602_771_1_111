// HabitProfileService.java
package com.example.demo.service;

import com.example.demo.model.HabitProfile;

import java.util.List;
import java.util.Optional;

public interface HabitProfileService {

    HabitProfile createOrUpdateHabit(HabitProfile habit);

    Optional<HabitProfile> getHabitById(Long id);

    HabitProfile getHabitByStudent(Long studentId);

    List<HabitProfile> getAllHabitProfiles();
}
