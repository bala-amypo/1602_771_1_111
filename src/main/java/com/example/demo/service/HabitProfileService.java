package com.example.demo.service;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;

public interface HabitProfileService {
    HabitProfile createOrUpdate(Long studentId, HabitProfileDto dto);
    HabitProfile getForStudent(Long studentId);
}