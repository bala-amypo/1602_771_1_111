package com.example.demo.dto;

public class HabitProfileDto {
    private Long id;
    private Long studentId;
    private Boolean smoking;
    private Boolean drinking;
    private String sleepTime;
    private String wakeTime;
    private String cleanlinessLevel;
    private String noisePreference;
    private String studyStyle;
    private String socialPreference;
    private String visitorsFrequency;

    public HabitProfileDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public Boolean getSmoking() { return smoking; }
    public void setSmoking(Boolean smoking) { this.smoking = smoking; }
    
    public Boolean getDrinking() { return drinking; }
    public void setDrinking(Boolean drinking) { this.drinking = drinking; }
    
    public String getSleepTime() { return sleepTime; }
    public void setSleepTime(String sleepTime) { this.sleepTime = sleepTime; }
    
    public String getWakeTime() { return wakeTime; }
    public void setWakeTime(String wakeTime) { this.wakeTime = wakeTime; }
    
    public String getCleanlinessLevel() { return cleanlinessLevel; }
    public void setCleanlinessLevel(String cleanlinessLevel) { this.cleanlinessLevel = cleanlinessLevel; }
    
    public String getNoisePreference() { return noisePreference; }
    public void setNoisePreference(String noisePreference) { this.noisePreference = noisePreference; }
    
    public String getStudyStyle() { return studyStyle; }
    public void setStudyStyle(String studyStyle) { this.studyStyle = studyStyle; }
    
    public String getSocialPreference() { return socialPreference; }
    public void setSocialPreference(String socialPreference) { this.socialPreference = socialPreference; }
    
    public String getVisitorsFrequency() { return visitorsFrequency; }
    public void setVisitorsFrequency(String visitorsFrequency) { this.visitorsFrequency = visitorsFrequency; }
}