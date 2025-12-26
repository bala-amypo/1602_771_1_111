// HabitProfileDto.java
package com.example.demo.dto;

public class HabitProfileDto {

    private Long studentId;
    private Integer studyHoursPerDay;
    private String sleepSchedule;
    private String cleanlinessLevel;
    private String noiseTolerance;
    private String socialPreference;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Integer getStudyHoursPerDay() { return studyHoursPerDay; }
    public void setStudyHoursPerDay(Integer studyHoursPerDay) { this.studyHoursPerDay = studyHoursPerDay; }

    public String getSleepSchedule() { return sleepSchedule; }
    public void setSleepSchedule(String sleepSchedule) { this.sleepSchedule = sleepSchedule; }

    public String getCleanlinessLevel() { return cleanlinessLevel; }
    public void setCleanlinessLevel(String cleanlinessLevel) { this.cleanlinessLevel = cleanlinessLevel; }

    public String getNoiseTolerance() { return noiseTolerance; }
    public void setNoiseTolerance(String noiseTolerance) { this.noiseTolerance = noiseTolerance; }

    public String getSocialPreference() { return socialPreference; }
    public void setSocialPreference(String socialPreference) { this.socialPreference = socialPreference; }
}
