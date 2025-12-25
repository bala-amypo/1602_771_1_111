package com.example.demo.service.impl;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CleanlinessLevel;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.NoiseTolerance;
import com.example.demo.model.SocialPreference;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {
    private final HabitProfileRepository habitProfileRepository;
    private final StudentProfileRepository studentProfileRepository;

    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository,
                                  StudentProfileRepository studentProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public HabitProfile createOrUpdate(Long studentId, HabitProfileDto dto) {
        StudentProfile student = studentProfileRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        HabitProfile habit = habitProfileRepository.findByStudentId(studentId)
            .orElse(new HabitProfile());

        habit.setStudent(student);
        habit.setSmoking(dto.getSmoking());
        habit.setDrinking(dto.getDrinking());
        habit.setSleepTime(dto.getSleepTime());
        habit.setWakeTime(dto.getWakeTime());
        
        if (dto.getCleanlinessLevel() != null) {
            habit.setCleanlinessLevel(CleanlinessLevel.valueOf(dto.getCleanlinessLevel()));
        }
        
        if (dto.getNoisePreference() != null) {
            habit.setNoisePreference(NoiseTolerance.valueOf(dto.getNoisePreference()));
        }
        
        habit.setStudyStyle(dto.getStudyStyle());
        
        if (dto.getSocialPreference() != null) {
            habit.setSocialPreference(SocialPreference.valueOf(dto.getSocialPreference()));
        }
        
        habit.setVisitorsFrequency(dto.getVisitorsFrequency());

        return habitProfileRepository.save(habit);
    }

    @Override
    public HabitProfile getForStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));
    }
}