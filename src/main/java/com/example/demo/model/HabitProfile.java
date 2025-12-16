package com.example.demo.model;

import java.time.LocalDateTime;

public class HabitProfile {

    private long id;
    private long studentId;

    private SleepSchedule sleepSchedule;
    private int studyHoursPerDay;
    private CleanlinessLevel cleanlinessLevel;
    private NoiseTolerance noiseTolerance;
    private SocialPreference socialPreference;

    private LocalDateTime updatedAt;

    public HabitProfile() {
    }

    public HabitProfile(long studentId,
                        SleepSchedule sleepSchedule,
                        int studyHoursPerDay,
                        CleanlinessLevel cleanlinessLevel,
                        NoiseTolerance noiseTolerance,
                        SocialPreference socialPreference,
                        LocalDateTime updatedAt) {

        if (studyHoursPerDay < 0) {
            throw new IllegalArgumentException("study hours invalid");
        }

        this.studentId = studentId;
        this.sleepSchedule = sleepSchedule;
        this.studyHoursPerDay = studyHoursPerDay;
        this.cleanlinessLevel = cleanlinessLevel;
        this.noiseTolerance = noiseTolerance;
        this.socialPreference = socialPreference;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public SleepSchedule getSleepSchedule() {
        return sleepSchedule;
    }

    public void setSleepSchedule(SleepSchedule sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public int getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(int studyHoursPerDay) {
        if (studyHoursPerDay < 0) {
            throw new IllegalArgumentException("study hours invalid");
        }
        this.studyHoursPerDay = studyHoursPerDay;
    }

    public CleanlinessLevel getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(CleanlinessLevel cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public NoiseTolerance getNoiseTolerance() {
        return noiseTolerance;
    }

    public void setNoiseTolerance(NoiseTolerance noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public SocialPreference getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(SocialPreference socialPreference) {
        this.socialPreference = socialPreference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
