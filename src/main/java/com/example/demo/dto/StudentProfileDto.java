package com.example.demo.dto;

import java.time.LocalTime;

public class StudentProfileDto {
    private Long id;
    private Long userId;
    private String name;
    private Integer age;
    private String course;
    private Integer yearOfStudy;
    private String gender;
    private String roomTypePreference;
    private LocalTime sleepTime;
    private LocalTime wakeTime;
    private Boolean smoking;
    private Boolean drinking;
    private String noiseTolerance;
    private Integer studyTime;

    public StudentProfileDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    
    public Integer getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(Integer yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getRoomTypePreference() { return roomTypePreference; }
    public void setRoomTypePreference(String roomTypePreference) { this.roomTypePreference = roomTypePreference; }
    
    public LocalTime getSleepTime() { return sleepTime; }
    public void setSleepTime(LocalTime sleepTime) { this.sleepTime = sleepTime; }
    
    public LocalTime getWakeTime() { return wakeTime; }
    public void setWakeTime(LocalTime wakeTime) { this.wakeTime = wakeTime; }
    
    public Boolean getSmoking() { return smoking; }
    public void setSmoking(Boolean smoking) { this.smoking = smoking; }
    
    public Boolean getDrinking() { return drinking; }
    public void setDrinking(Boolean drinking) { this.drinking = drinking; }
    
    public String getNoiseTolerance() { return noiseTolerance; }
    public void setNoiseTolerance(String noiseTolerance) { this.noiseTolerance = noiseTolerance; }
    
    public Integer getStudyTime() { return studyTime; }
    public void setStudyTime(Integer studyTime) { this.studyTime = studyTime; }
}